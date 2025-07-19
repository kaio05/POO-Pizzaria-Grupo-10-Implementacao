package br.edu.ufersa.poo.pizzaria.view;

import br.edu.ufersa.poo.pizzaria.model.entities.Cargo;
import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioService;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Tela extends Application {
    public static Stage stage;
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        telaLogin();
    }
    public static void telaLogin(){
        UsuarioService usuarioService = new UsuarioServiceImpl(EMSingleton.getInstance());
        usuarioService.seed(new Usuario("Michelangelo", "mich@email.com", "pizza", Cargo.ADMIN));
        FXMLLoader fxmlLoader = new FXMLLoader(Tela.class.getResource("/br/edu/ufersa/poo/pizzaria/TelaLogin.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void telaCadastro(){
        FXMLLoader fxmlLoader = new FXMLLoader(Tela.class.getResource("/br/edu/ufersa/poo/pizzaria/TelaCadastro.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void telaPrincipal(){
        FXMLLoader fxmlLoader = new FXMLLoader(Tela.class.getResource("/br/edu/ufersa/poo/pizzaria/TelaPrincipal.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main (String[] ags){
        launch();
    }
}
