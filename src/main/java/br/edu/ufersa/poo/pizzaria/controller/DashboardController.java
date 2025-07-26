package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.Pedido;
import br.edu.ufersa.poo.pizzaria.model.services.PedidoService;
import br.edu.ufersa.poo.pizzaria.model.services.PedidoServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class DashboardController {
    @FXML private AnchorPane root;

    @FXML public void initialize() {
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
        try {
            AnchorPane sidebar = sidebarLoader.load();
            root.getChildren().add(sidebar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void gerarPdf() {
        PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
        List<Pedido> pedidos = pedidoService.getAll();
        System.out.println(pedidos);
    }

}
