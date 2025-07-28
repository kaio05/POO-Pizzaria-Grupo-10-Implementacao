package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.*;
import br.edu.ufersa.poo.pizzaria.model.services.ClienteServiceImpl;
import br.edu.ufersa.poo.pizzaria.model.services.PedidoService;
import br.edu.ufersa.poo.pizzaria.model.services.PedidoServiceImpl;
import br.edu.ufersa.poo.pizzaria.model.services.TipoPizzaServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import br.edu.ufersa.poo.pizzaria.utils.RelatorioPedidosPDF;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardController {
    @FXML private AnchorPane root;
    @FXML private Label nPedidos;
    @FXML private Label adicionalMaisUsado;
    @FXML private Label totalVendas;
    @FXML private Label nClientes;
    @FXML private DatePicker inicioInput;
    @FXML private DatePicker fimInput;
    @FXML private ChoiceBox clienteBox;
    @FXML private ChoiceBox pizzaBox;
    List<Pedido> pedidos;
    List<Pedido> pedidosFiltrados;

    @FXML public void initialize() {
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
        try {
            AnchorPane sidebar = sidebarLoader.load();
            root.getChildren().add(sidebar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String> nomesClientes = new ClienteServiceImpl(EMSingleton.getInstance()).getAll().stream().map(Cliente::getNome).toList();
        clienteBox.getItems().add("Todos");
        clienteBox.setValue("Todos");
        pizzaBox.getItems().add("Todos");
        pizzaBox.setValue("Todos");
        clienteBox.getItems().addAll(nomesClientes);
        List<String> tiposPizza = new TipoPizzaServiceImpl(EMSingleton.getInstance()).getAll().stream().map(TipoPizza::getNome).toList();
        pizzaBox.getItems().addAll(tiposPizza);
        clienteBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> filtrarPedidos());

        PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
        pedidos = pedidoService.getAll();
        mostrarPedidos(pedidos);
    }

    @FXML public void filtrarPedidos() {
        pedidosFiltrados = pedidos;
        if(inicioInput.getValue() != null) {
            pedidosFiltrados = pedidosFiltrados.stream().filter(pedido -> pedido.getData().toLocalDate().isAfter(inicioInput.getValue())).toList();
            System.out.println(pedidos.getFirst().getData());
            System.out.println(pedidos.getFirst().getData().toLocalDate().isAfter(inicioInput.getValue()));
        }
        if(fimInput.getValue() != null) {
            pedidosFiltrados = pedidosFiltrados.stream().filter(pedido -> pedido.getData().toLocalDate().isBefore(fimInput.getValue())).toList();
        }
        if(clienteBox.getValue() != "Todos") {
            pedidosFiltrados = pedidosFiltrados.stream().filter(pedido -> pedido.getCliente().getNome().equals(clienteBox.getValue())).toList();
        }
        if(pizzaBox.getValue() != "Todos") {
            pedidosFiltrados = pedidosFiltrados.stream().filter(pedido -> pedido.getPizza().getPizza().equals(pizzaBox.getValue())).toList();
        }
        mostrarPedidos(pedidosFiltrados);
    }

    @FXML public void mostrarPedidos(List<Pedido> pedidos) {
        nPedidos.setText("N° de pedidos: " + String.valueOf(pedidos.size()));
        double vendas = 0;
        Map<Adicional, Integer> adicionais = new HashMap<>();
        for (Pedido pedido : pedidos) {
            double valorAdicionais = 0;
            for (Adicional adicional: pedido.getAdicional()) {
                valorAdicionais += adicional.getValor();
                adicionais.put(adicional, adicionais.getOrDefault(adicional, 0) + 1);
            }
            vendas += pedido.getPizza().getPizza().getValor() * (pedido.getTamanho() == Tamanho.P? 1: pedido.getTamanho() == Tamanho.M? 1.3: 1.5) + valorAdicionais;
        }
        totalVendas.setText("Vendas totais: R$" + String.valueOf(vendas));
        Adicional maisUsado = new Adicional();
        maisUsado.setNome("");
        int maisUsadoQtd = 0;
        for (Map.Entry<Adicional, Integer> adicional : adicionais.entrySet()) {
            if(adicional.getValue() > maisUsadoQtd) {
                maisUsadoQtd = adicional.getValue();
                maisUsado = adicional.getKey();
            }
        }
        adicionalMaisUsado.setText("Adicional mais usado: " + maisUsado.getNome());
        List<Cliente> clientes = new ClienteServiceImpl(EMSingleton.getInstance()).getAll();
        nClientes.setText("Novos clientes: " + String.valueOf(clientes.size()));
    }

    @FXML public void gerarPdf() throws Exception {
        PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
        List<Pedido> pedidos = pedidoService.getAll();
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String nome = ".\\relatorios\\relatorio_pedidos_" + agora.format(formatter) + ".pdf";
        RelatorioPedidosPDF.gerarPDF(pedidos, nome);
    }
}