package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.builder.PedidoBuilderImpl;
import br.edu.ufersa.poo.pizzaria.model.entities.*;
import br.edu.ufersa.poo.pizzaria.model.services.*;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import br.edu.ufersa.poo.pizzaria.view.Tela;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardPedidosController {

    // Componentes FXML
    @FXML private TextField cliente;
    @FXML private TextField sabor;
    @FXML private ScrollPane infoPedidos;
    @FXML private Pane root;
    @FXML private VBox listaAdicionais;
    @FXML private VBox container;
    @FXML private ChoiceBox buscarCliente;
    @FXML private ChoiceBox buscarSabor;

    // Serviços
    private final PedidoBuilderImpl pedidoBuilder = new PedidoBuilderImpl();
    private final AdicionalService adicionalService = new AdicionalServiceImpl(EMSingleton.getInstance());
    private final ClienteService clienteService = new ClienteServiceImpl(EMSingleton.getInstance());
    private final PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
    private final PizzaService pizzaService = new PizzaServiceImpl(EMSingleton.getInstance());
    private final TipoPizzaService tipoPizzaService = new TipoPizzaServiceImpl(EMSingleton.getInstance());

    @FXML
    public void initialize() {
        try {
            if (root == null) {
                throw new IllegalStateException("Root AnchorPane não foi injetado corretamente");
            }

            carregarSidebar();
            carregarAdicionais();
            buscarCliente.getItems().add("Todos");
            buscarCliente.setValue("Todos");
            clienteService.getAll().forEach(cliente -> buscarCliente.getItems().add(cliente.getCpf()));
            buscarCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> filtrarPedidos());
            buscarSabor.getItems().add("Todos");
            buscarSabor.setValue("Todos");
            tipoPizzaService.getAll().forEach(tp -> buscarSabor.getItems().add(tp.getCodigo()));
            buscarSabor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> filtrarPedidos());
            exibirPedidos(pedidoService.getAll());

        } catch (IOException e) {
            mostrarErro("Erro ao carregar componentes: " + e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro na inicialização: " + e.getMessage());
        }
    }

    private void carregarSidebar() throws IOException {
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
        Node sidebar = sidebarLoader.load();
        root.getChildren().add(sidebar);
        AnchorPane.setTopAnchor(sidebar, 0.0);
        AnchorPane.setLeftAnchor(sidebar, 0.0);
        AnchorPane.setBottomAnchor(sidebar, 0.0);
    }

    private void carregarAdicionais() {
        if (listaAdicionais == null) {
            System.err.println("Lista de adicionais não foi injetada corretamente");
            return;
        }

        listaAdicionais.getChildren().clear();
        List<Adicional> adicionais = adicionalService.getAll();

        if (adicionais.isEmpty()) {
            Label aviso = new Label("Nenhum adicional disponível");
            aviso.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
            listaAdicionais.getChildren().add(aviso);
            return;
        }

        adicionais.forEach(adicional -> {
            CheckBox checkBox = new CheckBox(adicional.getNome());
            checkBox.setUserData(adicional);
            listaAdicionais.getChildren().add(checkBox);
        });
    }

    @FXML
    private void criarPedido() {
        try {
            System.out.println("Botão fazer pedido clicado!"); // Log para depuração

            if (cliente.getText() == null || cliente.getText().trim().isEmpty()) {
                mostrarErro("Por favor, informe o nome do cliente!");
                cliente.requestFocus();
                return;
            }

            if (sabor.getText() == null || sabor.getText().trim().isEmpty()) {
                mostrarErro("Por favor, informe o sabor da pizza!");
                sabor.requestFocus();
                return;
            }

            List<Adicional> adicionaisSelecionados = obterAdicionaisSelecionados();
            System.out.println("Adicionais selecionados: " + adicionaisSelecionados.size());

            Tamanho tamanho = Tamanho.M;
            System.out.println("Tamanho selecionado: " + tamanho);

            Cliente clientePedido = buscarCliente(cliente.getText().trim());
            System.out.println("Cliente encontrado: " + clientePedido.getNome());

            TipoPizza tipo = tipoPizzaService.getAll().stream().filter(t -> t.getNome().equalsIgnoreCase(sabor.getText().trim())).toList().getFirst();
            Pizza pizzaPedido = new Pizza(tipo, clientePedido);
            pizzaService.register(pizzaPedido);
            System.out.println("Pizza encontrada: " + pizzaPedido.getPizza().getNome());

            Pedido novoPedido = pedidoBuilder
                    .withCliente(clientePedido)
                    .withPizza(pizzaPedido)
                    .withAdicionais(adicionaisSelecionados)
                    .withEstado(Estado.NOVO)
                    .withTamanho(tamanho)
                    .withData(Date.valueOf(LocalDate.now()))
                    .build();

            System.out.println("Pedido construído: " + novoPedido.getId());

            // 7. Registrar no banco de dados
            System.out.println("Novo pedido: " + novoPedido);
            pedidoService.register(novoPedido);
            System.out.println("Pedido registrado com sucesso!");

            // 8. Feedback e limpeza
            atualizarListaPedidos();
            mostrarSucesso("Pedido #" + novoPedido.getId() + " criado com sucesso!");
            Tela.pedidos();

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao criar pedido: " + e.getMessage());
            e.printStackTrace(); // Log completo do erro
        }
    }

    private List<Adicional> obterAdicionaisSelecionados() {
        if (listaAdicionais == null) return new ArrayList<>();

        return listaAdicionais.getChildren().stream()
                .filter(node -> node instanceof CheckBox)
                .map(node -> (CheckBox) node)
                .filter(CheckBox::isSelected)
                .map(cb -> (Adicional) cb.getUserData())
                .collect(Collectors.toList());
    }

    private void atualizarListaPedidos() {
        exibirPedidos(pedidoService.getAll());
    }

    private void exibirPedidos(List<Pedido> pedidos) {
        if (infoPedidos == null) return;

        pedidos.forEach(pedido -> {
            System.out.println(pedido);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/PedidoRegister.fxml"));
                Pane registro = loader.load();
                PedidoRegisterController controller = loader.getController();
                controller.carregarPedido(pedido);
                container.getChildren().add(registro);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String formatarPedido(Pedido pedido) {
        return String.format("#%d - %s: %s (%s) - %s - Adicionais: %s",
                pedido.getId(),
                pedido.getCliente().getNome(),
                pedido.getPizza().getPizza().getNome(),
                pedido.getTamanho(),
                pedido.getEstado(),
                pedido.getAdicional().stream()
                        .map(Adicional::getNome)
                        .collect(Collectors.joining(", ")));
    }

    private Cliente buscarCliente(String nome) {
        return clienteService.getAll().stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + nome));
    }

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML public void filtrarPedidos() {
        container.getChildren().clear();
        List<Pedido> pedidosFiltrados = pedidoService.getAll();
        if(buscarCliente.getValue() != "Todos")
            pedidosFiltrados = pedidosFiltrados.stream().filter(pedido -> pedido.getCliente().getCpf().equals(buscarCliente.getValue())).toList();
        if(buscarSabor.getValue() != "Todos")
            pedidosFiltrados = pedidosFiltrados.stream().filter(pedido -> pedido.getPizza().getPizza().getCodigo().equals(buscarSabor.getValue())).toList();
        exibirPedidos(pedidosFiltrados);
    }

}
