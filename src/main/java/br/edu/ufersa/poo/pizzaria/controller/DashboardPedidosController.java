/*package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.builder.PedidoBuilderImpl;
import br.edu.ufersa.poo.pizzaria.model.entities.*;
import br.edu.ufersa.poo.pizzaria.model.services.*;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DashboardPedidosController {

    // Componentes FXML
    @FXML private AnchorPane root;
    @FXML private TextField cliente;
    @FXML private VBox saborLista;
    @FXML private TextField procurar;
    @FXML private ScrollPane infoPedidos;
    @FXML private VBox listaAdicionais;
    @FXML private ChoiceBox<String> checkTipo;
    @FXML private ChoiceBox<String> checkEstado;
    @FXML private Button buttonFazerPedido;

    // Serviços
    private final PedidoBuilderImpl pedidoBuilder = new PedidoBuilderImpl();
    private final AdicionalService adicionalService = new AdicionalServiceImpl(EMSingleton.getInstance());
    private final ClienteService clienteService = new ClienteServiceImpl(EMSingleton.getInstance());
    private final PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
    private final TipoPizzaService tipoPizzaService = new TipoPizzaServiceImpl(EMSingleton.getInstance());

    @FXML
    public void initialize() {
        try {
            carregarSidebar();
            configurarComponentes();
            carregarDadosIniciais();
        } catch (Exception e) {
            mostrarErro("Erro na inicialização: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void carregarSidebar() throws IOException {
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
        AnchorPane sidebar = sidebarLoader.load();
        root.getChildren().add(sidebar);
        AnchorPane.setTopAnchor(sidebar, 0.0);
        AnchorPane.setLeftAnchor(sidebar, 0.0);
        AnchorPane.setBottomAnchor(sidebar, 0.0);
    }

    private void configurarComponentes() {
        // Configura ChoiceBoxes
        checkTipo.getItems().addAll("Pequena", "Média", "Grande");
        checkTipo.setValue("Média");

        checkEstado.getItems().addAll("Novo", "Preparando", "Assando", "Pronto", "Entregue");
        checkEstado.setValue("Novo");

        // Configura botão
        buttonFazerPedido.setOnAction(e -> criarPedido());
    }

    private void carregarDadosIniciais() {
        // Carrega sabores
        carregarSabores();

        // Carrega adicionais
        carregarAdicionais();

        // Carrega pedidos
        atualizarListaPedidos();
    }

    private void carregarSabores() {
        saborLista.getChildren().clear();
        List<TipoPizza> sabores = tipoPizzaService.getAll();

        sabores.forEach(sabor -> {
            CheckBox checkBox = new CheckBox(sabor.getNome());
            checkBox.setUserData(sabor); // Armazena o objeto TipoPizza
            saborLista.getChildren().add(checkBox);
        });
    }

    private void carregarAdicionais() {
        listaAdicionais.getChildren().clear();
        List<Adicional> adicionais = adicionalService.getAll();

        adicionais.forEach(adicional -> {
            CheckBox checkBox = new CheckBox(adicional.getNome() + " - R$ " + adicional.getValor());
            checkBox.setUserData(adicional);
            listaAdicionais.getChildren().add(checkBox);
        });
    }

    @FXML
    private void criarPedido() {
        try {
            // Obtém dados do formulário
            String nomeCliente = cliente.getText();
            List<TipoPizza> saboresSelecionados = obterSaboresSelecionados();
            List<Adicional> adicionaisSelecionados = obterAdicionaisSelecionados();
            Tamanho tamanho = obterTamanhoSelecionado();
            Estado estado = obterEstadoSelecionado();

            // Validações
            if (nomeCliente.isEmpty() || saboresSelecionados.isEmpty()) {
                mostrarErro("Preencha todos os campos obrigatórios!");
                return;
            }

            // Busca ou cria cliente
            Cliente clientePedido = clienteService.getAll().stream()
                    .filter(c -> c.getNome().equalsIgnoreCase(nomeCliente))
                    .findFirst()
                    .orElseGet(() -> {
                        Cliente novo = new Cliente(nomeCliente);
                        clienteService.register(novo);
                        return novo;
                    });

            // Cria pedido para cada sabor selecionado
            for (TipoPizza sabor : saboresSelecionados) {
                Pedido novoPedido = pedidoBuilder
                        .withCliente(clientePedido)
                        .withPizza(new Pizza(sabor, tamanho))
                        .withAdicionais(adicionaisSelecionados)
                        .withEstado(estado)
                        .withData(Date.valueOf(LocalDate.now()))
                        .build();

                pedidoService.register(novoPedido);
            }

            // Feedback e limpeza
            mostrarSucesso("Pedido(s) criado(s) com sucesso!");
            limparCampos();
            atualizarListaPedidos();

        } catch (Exception e) {
            mostrarErro("Erro ao criar pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private List<TipoPizza> obterSaboresSelecionados() {
        return saborLista.getChildren().stream()
                .filter(node -> node instanceof CheckBox)
                .map(node -> (CheckBox) node)
                .filter(CheckBox::isSelected)
                .map(cb -> (TipoPizza) cb.getUserData())
                .collect(Collectors.toList());
    }

    private List<Adicional> obterAdicionaisSelecionados() {
        return listaAdicionais.getChildren().stream()
                .filter(node -> node instanceof CheckBox)
                .map(node -> (CheckBox) node)
                .filter(CheckBox::isSelected)
                .map(cb -> (Adicional) cb.getUserData())
                .collect(Collectors.toList());
    }

    private Tamanho obterTamanhoSelecionado() {
        return Tamanho.valueOf(checkTipo.getValue().toUpperCase());
    }

    private Estado obterEstadoSelecionado() {
        return Estado.valueOf(checkEstado.getValue().toUpperCase());
    }

    private void atualizarListaPedidos() {
        List<Pedido> pedidos = pedidoService.getAll();
        exibirPedidos(pedidos);
    }

    private void exibirPedidos(List<Pedido> pedidos) {
        VBox container = new VBox(5);

        if (pedidos.isEmpty()) {
            container.getChildren().add(new Label("Nenhum pedido encontrado"));
        } else {
            pedidos.forEach(pedido -> {
                Label label = new Label(formatarPedido(pedido));
                label.setStyle("-fx-padding: 5; -fx-border-color: #ddd;");
                container.getChildren().add(label);
            });
        }

        infoPedidos.setContent(container);
    }

    private String formatarPedido(Pedido pedido) {
        return String.format("#%d - %s: %s (%s) - %s - Adicionais: %s",
                pedido.getId(),
                pedido.getCliente().getNome(),
                pedido.getPizza().getPizza().getNome(),
                pedido.getTamanho(),
                pedido.getEstado(),
                pedido.getAdicional().isEmpty() ? "Nenhum" :
                        pedido.getAdicional().stream()
                                .map(Adicional::getNome)
                                .collect(Collectors.joining(", ")));
    }

    private void limparCampos() {
        cliente.clear();
        saborLista.getChildren().forEach(node -> {
            if (node instanceof CheckBox) {
                ((CheckBox) node).setSelected(false);
            }
        });
        listaAdicionais.getChildren().forEach(node -> {
            if (node instanceof CheckBox) {
                ((CheckBox) node).setSelected(false);
            }
        });
        checkTipo.setValue("Média");
        checkEstado.setValue("Novo");
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
}*/