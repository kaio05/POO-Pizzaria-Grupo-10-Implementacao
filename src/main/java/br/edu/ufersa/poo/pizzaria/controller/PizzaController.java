package br.edu.ufersa.poo.pizzaria.controller;

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
import java.util.List;
import java.util.stream.Collectors;

public class PizzaController {

    // Componentes FXML
    @FXML private AnchorPane root;
    @FXML private TextField buscarClientes;
    @FXML private ComboBox<String> buscarTipo;
    @FXML private ScrollPane mostrarPizzas;

    // Serviços
    private final PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
    private final TipoPizzaService tipoPizzaService = new TipoPizzaServiceImpl(EMSingleton.getInstance());
    private final ClienteService clienteService = new ClienteServiceImpl(EMSingleton.getInstance());

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
        Node sidebar = loader.load();
        root.getChildren().add(sidebar);
        AnchorPane.setTopAnchor(sidebar, 0.0);
        AnchorPane.setLeftAnchor(sidebar, 0.0);
        AnchorPane.setBottomAnchor(sidebar, 0.0);
    }

    private void configurarComponentes() {
        // Configura listeners para busca automática
        buscarClientes.textProperty().addListener((obs, oldVal, newVal) -> buscar_clientes());
        buscarTipo.valueProperty().addListener((obs, oldVal, newVal) -> buscar_tipo());
    }

    private void carregarDadosIniciais() {
        // Carrega tipos de pizza disponíveis
        List<TipoPizza> tipos = tipoPizzaService.getAll();
        buscarTipo.getItems().setAll(tipos.stream()
                .map(TipoPizza::getNome)
                .collect(Collectors.toList()));

        // Carrega pedidos iniciais
        buscar_clientes();
    }

    @FXML
    private void buscar_clientes() {
        String nomeCliente = buscarClientes.getText().trim();
        List<Pedido> pedidosFiltrados;

        if (nomeCliente.isEmpty()) {
            pedidosFiltrados = pedidoService.getAll();
        } else {
            pedidosFiltrados = pedidoService.getByClienteNome(nomeCliente);
        }

        exibirPizzas(pedidosFiltrados);
    }

    @FXML
    private void buscar_tipo() {
        String tipoPizza = buscarTipo.getValue();
        List<Pedido> pedidosFiltrados;

        if (tipoPizza == null || tipoPizza.isEmpty()) {
            pedidosFiltrados = pedidoService.getAll();
        } else {
            pedidosFiltrados = pedidoService.getByTipoPizza(tipoPizza);
        }

        exibirPizzas(pedidosFiltrados);
    }

    @FXML
    private void procurar() {
        // Decide qual busca fazer baseado em qual campo está preenchido
        if (!buscarClientes.getText().isEmpty()) {
            buscar_clientes();
        } else if (buscarTipo.getValue() != null) {
            buscar_tipo();
        } else {
            // Se nenhum critério especificado, mostra todos
            exibirPizzas(pedidoService.getAll());
        }
    }

    private void exibirPizzas(List<Pedido> pedidos) {
        VBox container = new VBox(5);

        if (pedidos.isEmpty()) {
            container.getChildren().add(new Label("Nenhum pedido encontrado"));
        } else {
            for (Pedido pedido : pedidos) {
                VBox card = criarCardPedido(pedido);
                container.getChildren().add(card);
            }
        }

        mostrarPizzas.setContent(container);
    }

    private VBox criarCardPedido(Pedido pedido) {
        VBox card = new VBox(5);
        card.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: #ddd; -fx-border-radius: 5;");

        Label lblCliente = new Label("Cliente: " + pedido.getCliente().getNome());
        Label lblSabor = new Label("Sabor: " + pedido.getPizza().getPizza().getNome());
        Label lblTamanho = new Label("Tamanho: " + pedido.getTamanho());
        Label lblValor = new Label("Valor: R$ " + calcularValorTotal(pedido));

        card.getChildren().addAll(lblCliente, lblSabor, lblTamanho, lblValor);
        return card;
    }

    private double calcularValorTotal(Pedido pedido) {
        double valorBase = pedido.getPizza().getPizza().getValor();
        double valorTamanho = valorBase * pedido.getTamanho().getMultiplicador();
        double valorAdicionais = pedido.getAdicional().stream()
                .mapToDouble(Adicional::getValor)
                .sum();

        return valorTamanho + valorAdicionais;
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
