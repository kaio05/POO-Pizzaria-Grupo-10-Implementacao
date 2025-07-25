package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.Cargo;
import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioService;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import br.edu.ufersa.poo.pizzaria.view.Tela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class UsuariosController {
    @FXML private VBox usuarioContainer;
    @FXML private AnchorPane root;
    @FXML private Label erro;
    @FXML private TextField novoUsuarioNome;
    @FXML private TextField novoUsuarioEmail;
    @FXML private TextField novoUsuarioSenha;
    @FXML private Button buttonCriarUsuario;
    @FXML private Button buttonEditarUsuario;
    private static volatile UsuariosController controller;
    List<Usuario> usuarios;

    public static UsuariosController getInstance() {
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

        if(usuarios == null) {
            usuarios = service.getAll();
            usuarios.forEach(usuario -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/UsuarioRegister.fxml"));
                    Pane registro = loader.load();
                    UsuarioRegisterController controller = loader.getController();
                    controller.setLabelNome(usuario.getNome());
                    controller.setLabelEmail(usuario.getEmail());
                    controller.setSenha(usuario.getSenha());
                    controller.setLabelCargo(usuario.getCargo());
                    novoUsuarioNome.setText("");
                    novoUsuarioEmail.setText("");
                    novoUsuarioSenha.setText("");
                    usuarioContainer.getChildren().add(registro);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    UsuarioService service = new UsuarioServiceImpl(EMSingleton.getInstance());
    Usuario novoUsuario = new Usuario();

    @FXML public void criar_usuario() {
        novoUsuario.setNome(novoUsuarioNome.getText());
        novoUsuario.setEmail(novoUsuarioEmail.getText());
        novoUsuario.setSenha(novoUsuarioSenha.getText());
        novoUsuario.setCargo(Cargo.COMUM);
        try {
            service.register(novoUsuario);
            erro.setVisible(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/UsuarioRegister.fxml"));
            Pane registro = loader.load();
            UsuarioRegisterController controller = loader.getController();
            controller.setLabelNome(novoUsuario.getNome());
            controller.setLabelEmail(novoUsuario.getEmail());
            controller.setSenha(novoUsuario.getSenha());
            controller.setLabelCargo(novoUsuario.getCargo());
            usuarioContainer.getChildren().add(registro);
        } catch (Exception e) {
            erro.setText(e.getMessage());
            erro.setTextFill(Color.RED);
            erro.setVisible(true);
        }
    }

    @FXML public void edit_usuario(Usuario usuarioEditar) {
        this.buttonCriarUsuario.setVisible(false);
        this.buttonEditarUsuario.setVisible(true);
        novoUsuarioNome.setText(usuarioEditar.getNome());
        novoUsuarioEmail.setText(usuarioEditar.getEmail());
        novoUsuarioEmail.setDisable(true);
        novoUsuarioSenha.setText(usuarioEditar.getSenha());
    }

    @FXML public void update_usuario() {
        Usuario usuarioUpdate = new Usuario();
        usuarioUpdate.setEmail(novoUsuarioEmail.getText());
        usuarioUpdate.setId(service.getByEmail(usuarioUpdate).getId());
        usuarioUpdate.setNome(novoUsuarioNome.getText());
        usuarioUpdate.setSenha(novoUsuarioSenha.getText());
        usuarioUpdate.setCargo(Cargo.COMUM);
        service.update(usuarioUpdate);
        System.out.println(usuarioUpdate);
        Tela.usuarios();
    }
}
