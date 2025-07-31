package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.builder.PedidoBuilderImpl;
import br.edu.ufersa.poo.pizzaria.model.entities.*;
import br.edu.ufersa.poo.pizzaria.model.services.*;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DashboardPedidosController {

    // Componentes FXML
    @FXML private TextField cliente;
    @FXML private TextField sabor;
    @FXML private TitledPane pedidoAdicionais;
    @FXML private Button buttonFazerPedido;
    @FXML private TextField procurar;
    @FXML private MenuButton escolher;
    @FXML private ScrollPane infoPedidos;
    @FXML private AnchorPane root;
    @FXML private VBox listaAdicionais;
    @FXML private ChoiceBox<String> checkTipo;

    // Serviços
    private final PedidoBuilderImpl pedidoBuilder = new PedidoBuilderImpl();
    private final AdicionalService adicionalService = new AdicionalServiceImpl(EMSingleton.getInstance());
    private final ClienteService clienteService = new ClienteServiceImpl(EMSingleton.getInstance());
    private final PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
    private final PizzaService pizzaService = new PizzaServiceImpl(EMSingleton.getInstance());

    @FXML
    public void initialize() {
        try {
            if (root == null) {
                throw new IllegalStateException("Root AnchorPane não foi injetado corretamente");
            }

            carregarSidebar();
            carregarAdicionais();

            if (escolher != null) {
                configurarMenuFiltro();
            }

            if (buttonFazerPedido != null) {
                buttonFazerPedido.setOnAction(e -> criarPedido());
            }

            if (checkTipo != null) {
                configurarChoiceBoxTipo();
            }

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

    private void configurarChoiceBoxTipo() {
        checkTipo.getItems().addAll("Pequena (P)", "Média (M)", "Grande (G)");
        checkTipo.setValue("Média (M)");
    }

    private void configurarMenuFiltro() {
        MenuItem itemCliente = new MenuItem("Cliente");
        MenuItem itemSabor = new MenuItem("Sabor");
        MenuItem itemTodos = new MenuItem("Todos");

        itemCliente.setOnAction(e -> {
            escolher.setText("Filtrar por: Cliente");
            filtrarPedidos("cliente");
        });

        itemSabor.setOnAction(e -> {
            escolher.setText("Filtrar por: Sabor");
            filtrarPedidos("sabor");
        });

        itemTodos.setOnAction(e -> {
            escolher.setText("Filtrar por");
            filtrarPedidos("todos");
        });

        escolher.getItems().clear();
        escolher.getItems().addAll(itemCliente, itemSabor, itemTodos);
        escolher.setText("Filtrar por");
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

            Tamanho tamanho = determinarTamanho();
            System.out.println("Tamanho selecionado: " + tamanho);

            Cliente clientePedido = buscarCliente(cliente.getText().trim());
            System.out.println("Cliente encontrado: " + clientePedido.getNome());

            Pizza pizzaPedido = buscarPizza(sabor.getText().trim());
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
            pedidoService.register(novoPedido);
            System.out.println("Pedido registrado com sucesso!");

            // 8. Feedback e limpeza
            atualizarListaPedidos();
            limparCampos();
            mostrarSucesso("Pedido #" + novoPedido.getId() + " criado com sucesso!");

        } catch (IllegalArgumentException e) {
            mostrarErro(e.getMessage());
        } catch (Exception e) {
            mostrarErro("Erro inesperado ao criar pedido: " + e.getMessage());
            e.printStackTrace(); // Log completo do erro
        }
    }

    private Tamanho determinarTamanho() {
        if (checkTipo == null) return Tamanho.M;

        String selecionado = checkTipo.getValue();
        if (selecionado.contains("Pequena")) return Tamanho.P;
        if (selecionado.contains("Grande")) return Tamanho.G;
        return Tamanho.M;
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

    private void filtrarPedidos(String criterio) {
        if (procurar == null) return;

        String termo = procurar.getText().trim();
        List<Pedido> pedidosFiltrados = new ArrayList<>();

        switch (criterio.toLowerCase()) {
            case "cliente":
                if (!termo.isEmpty()) {
                    pedidosFiltrados = pedidoService.getByCliente(buscarCliente(termo));
                }
                break;
            case "sabor":
                if (!termo.isEmpty()) {
                    pedidosFiltrados = pedidoService.getAll().stream()
                            .filter(p -> p.getPizza().getPizza().getNome().equalsIgnoreCase(termo))
                            .collect(Collectors.toList());
                }
                break;
            default:
                pedidosFiltrados = pedidoService.getAll();
        }

        exibirPedidos(pedidosFiltrados.isEmpty() ? pedidoService.getAll() : pedidosFiltrados);
    }

    private void atualizarListaPedidos() {
        exibirPedidos(pedidoService.getAll());
    }

    private void exibirPedidos(List<Pedido> pedidos) {
        if (infoPedidos == null) return;

        VBox container = new VBox(5);
        pedidos.forEach(pedido -> {
            Label label = new Label(formatarPedido(pedido));
            label.setStyle("-fx-padding: 5; -fx-border-color: #ddd; -fx-border-width: 0 0 1 0;");
            container.getChildren().add(label);
        });

        infoPedidos.setContent(container);
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

    private void limparCampos() {
        if (cliente != null) cliente.clear();
        if (sabor != null) sabor.clear();
        if (listaAdicionais != null) {
            listaAdicionais.getChildren().forEach(node -> {
                if (node instanceof CheckBox) {
                    ((CheckBox) node).setSelected(false);
                }
            });
        }
        if (procurar != null) procurar.clear();
    }

    private Cliente buscarCliente(String nome) {
        return clienteService.getAll().stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + nome));
    }

    private Pizza buscarPizza(String nomeSabor) {
        return pizzaService.getAll().stream()
                .filter(p -> p.getPizza().getNome().equalsIgnoreCase(nomeSabor))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sabor não encontrado: " + nomeSabor));
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
}
