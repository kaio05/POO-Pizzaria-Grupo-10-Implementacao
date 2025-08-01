package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.exceptions.BadRequestException;
import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.model.services.ClienteService;
import br.edu.ufersa.poo.pizzaria.model.services.ClienteServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import br.edu.ufersa.poo.pizzaria.view.Tela;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class ClientesController {
    @FXML private VBox clienteContainer;
    @FXML private AnchorPane root;
    @FXML private Label erro;
    @FXML private TextField novoClienteNome;
    @FXML private TextField novoClienteCpf;
    @FXML private TextField novoClienteEndereco;
    @FXML private TextField novoClienteTelefone;
    @FXML private TextField buscaNome;
    @FXML private TextField buscaCpf;
    @FXML private TextField buscaEndereco;
    @FXML private TextField buscaTelefone;
    @FXML private Button buttonCriarCliente;
    @FXML private Button buttonEditarCliente;
    @FXML private AnchorPane popupBG;
    private static volatile ClientesController controller;
    List<Cliente> clientes;
    List<Cliente> clientesVisiveis;
    private Cliente clienteAntigo;

    public static ClientesController getInstance() {
        return controller;
    }

    @FXML public void initialize() {
        controller = this;
        erro.setVisible(false);
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/Sidebar.fxml"));
        try {
            AnchorPane sidebar = sidebarLoader.load();
            root.getChildren().add(sidebar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(clientes == null) {
            clientes = service.getAll();
            clientesVisiveis = clientes;
            mostrar_clientes();
        }
    }

    ClienteService service = new ClienteServiceImpl(EMSingleton.getInstance());
    Cliente novoCliente = new Cliente();

    @FXML public void abrir_popup() {
        popupBG.setVisible(true);
    }

    @FXML public void fechar_popup() {
        novoClienteNome.setText("");
        novoClienteCpf.setText("");
        novoClienteEndereco.setText("");
        novoClienteTelefone.setText("");
        popupBG.setVisible(false);
    }

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
            clienteContainer.getChildren().add(registro);
            Tela.clientes();
        } catch (Exception e) {
            erro.setText(e.getMessage());
            erro.setTextFill(Color.RED);
            erro.setVisible(true);
        }
    }

    @FXML public void edit_cliente(Cliente clienteEditar) {
        clienteAntigo = clienteEditar;
        abrir_popup();
        this.buttonCriarCliente.setVisible(false);
        this.buttonEditarCliente.setVisible(true);
        novoClienteNome.setText(clienteEditar.getNome());
        novoClienteCpf.setText(clienteEditar.getCpf());
        novoClienteEndereco.setText(clienteEditar.getEndereco());
        novoClienteTelefone.setText(clienteEditar.getTelefone());
    }

    @FXML public void update_cliente() {
        Cliente clienteUpdate = new Cliente();
        clienteUpdate.setCpf(novoClienteCpf.getText());
        clienteUpdate.setId(service.getByCpf(clienteAntigo).getId());
        clienteUpdate.setNome(novoClienteNome.getText());
        clienteUpdate.setEndereco(novoClienteEndereco.getText());
        clienteUpdate.setTelefone(novoClienteTelefone.getText());
        try{
            service.update(clienteUpdate);
            Tela.clientes();
        } catch (Exception e) {
            erro.setText("CPF já cadastrado");
            erro.setTextFill(Color.RED);
            erro.setVisible(true);
        }
    }

    @FXML public void filtrar_clientes() {
        clientesVisiveis = clientes;
        clienteContainer.getChildren().clear();
        if (!buscaNome.getText().isEmpty())
            clientesVisiveis = clientesVisiveis.stream().filter(cliente -> cliente.getNome().toLowerCase().contains(buscaNome.getText().toLowerCase())).toList();
        if (!buscaCpf.getText().isEmpty())
            clientesVisiveis = clientesVisiveis.stream().filter(cliente -> cliente.getCpf().contains(buscaCpf.getText())).toList();
        if (!buscaEndereco.getText().isEmpty())
            clientesVisiveis = clientesVisiveis.stream().filter(cliente -> cliente.getEndereco().toLowerCase().contains(buscaEndereco.getText().toLowerCase())).toList();
        if (!buscaTelefone.getText().isEmpty())
            clientesVisiveis = clientesVisiveis.stream().filter(cliente -> cliente.getTelefone().contains(buscaTelefone.getText())).toList();
        mostrar_clientes();
    }

    @FXML public void mostrar_clientes() {
        clientesVisiveis.forEach(cliente -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/ClienteRegister.fxml"));
                Pane registro = loader.load();
                ClienteRegisterController controller = loader.getController();
                controller.setLabelNome(cliente.getNome());
                controller.setLabelCpf(cliente.getCpf());
                controller.setLabelEndereco(cliente.getEndereco());
                controller.setLabelTelefone(cliente.getTelefone());
                clienteContainer.getChildren().add(registro);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
