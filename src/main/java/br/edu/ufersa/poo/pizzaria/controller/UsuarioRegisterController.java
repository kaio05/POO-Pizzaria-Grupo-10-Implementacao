package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.Cargo;
import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioService;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class UsuarioRegisterController {
    @FXML private Label nome;
    @FXML private Label email;
    @FXML private Label cargo;
    @FXML private Pane root;
    @FXML private ImageView trash;
    private String senha;

    public void setLabelNome(String nome) {
        this.nome.setText(nome);
    }

    public void setLabelEmail(String email) {
        this.email.setText(email);
    }

    public void setLabelCargo(Cargo cargo) {
        this.cargo.setText(cargo == Cargo.ADMIN ? "Admin" : "Comum");
        if(cargo == Cargo.ADMIN) {
            trash.setVisible(false);
        }
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void delete_usuario() {
        VBox parent = (VBox) root.getParent();
        parent.getChildren().remove(root);
        UsuarioService service = new UsuarioServiceImpl(EMSingleton.getInstance());
        Usuario usuario = new Usuario();
        usuario.setEmail(this.email.getText());
        Usuario usuarioRemover = service.getByEmail(usuario);
        service.remove(usuarioRemover);
    }

    public void edit_usuario() {
        UsuariosController controller = UsuariosController.getInstance();
        controller.edit_usuario(new Usuario(nome.getText(), email.getText(), senha, Cargo.COMUM));
    }
}
