package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.model.services.ClienteService;
import br.edu.ufersa.poo.pizzaria.model.services.ClienteServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class ClientesController {
    @FXML private VBox clientContainer;
    @FXML private AnchorPane root;
    @FXML private Label erro;

    @FXML public void initialize() {
        erro.setVisible(false);
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
        try {
            AnchorPane sidebar = sidebarLoader.load();
            root.getChildren().add(sidebar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Cliente> clientes = service.getAll();
        clientes.forEach(cliente -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/ClienteRegister.fxml"));
                Pane registro = loader.load();
                ClienteRegisterController controller = loader.getController();
                controller.setLabelNome(cliente.getNome());
                controller.setLabelCpf(cliente.getCpf());
                controller.setLabelEndereco(cliente.getEndereco());
                controller.setLabelTelefone(cliente.getTelefone());
                clientContainer.getChildren().add(registro);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML private TextField novoClienteNome;
    @FXML private TextField novoClienteCpf;
    @FXML private TextField novoClienteEndereco;
    @FXML private TextField novoClienteTelefone;

    ClienteService service = new ClienteServiceImpl(EMSingleton.getInstance());
    Cliente novoCliente = new Cliente();

    @FXML public void criar_cliente() {
        novoCliente.setNome(novoClienteNome.getText());
        novoCliente.setCpf(novoClienteCpf.getText());
        novoCliente.setEndereco(novoClienteEndereco.getText());
        novoCliente.setTelefone(novoClienteTelefone.getText());
        try {
            service.register(novoCliente);
            erro.setVisible(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/ClienteRegister.fxml"));
            Pane registro = loader.load();
            ClienteRegisterController controller = loader.getController();
            controller.setLabelNome(novoCliente.getNome());
            controller.setLabelCpf(novoCliente.getCpf());
            controller.setLabelEndereco(novoCliente.getEndereco());
            controller.setLabelTelefone(novoCliente.getTelefone());
            clientContainer.getChildren().add(registro);
        } catch (Exception e) {
            erro.setText(e.getMessage());
            erro.setTextFill(Color.RED);
            erro.setVisible(true);
        }
    }
}
