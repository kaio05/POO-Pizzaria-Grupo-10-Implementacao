package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.model.services.ClienteService;
import br.edu.ufersa.poo.pizzaria.model.services.ClienteServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ClienteRegisterController {
    @FXML private Label nome;
    @FXML private Label cpf;
    @FXML private Label endereco;
    @FXML private Label telefone;
    @FXML private VBox clienteContainer;
    @FXML private Pane root;

    public void setLabelNome(String nome) {
        this.nome.setText(nome);
    }

    public void setLabelCpf(String cpf) {
        this.cpf.setText(cpf);
    }

    public void setLabelEndereco(String endereco) {
        this.endereco.setText(endereco);
    }

    public void setLabelTelefone(String telefone) {
        this.telefone.setText(telefone);
    }

    public void delete_cliente() {
        VBox parent = (VBox) root.getParent();
        parent.getChildren().remove(root);
        ClienteService service = new ClienteServiceImpl(EMSingleton.getInstance());
        Cliente cliente = new Cliente();
        cliente.setCpf(this.cpf.getText());
        Cliente clienteRemover = service.getByCpf(cliente);
        service.remove(clienteRemover);
    }

    public void edit_cliente() {
        ClientesController controller = ClientesController.getInstance();
        controller.edit_cliente(new Cliente(nome.getText(), endereco.getText(),cpf.getText(), telefone.getText()));
    }
}
