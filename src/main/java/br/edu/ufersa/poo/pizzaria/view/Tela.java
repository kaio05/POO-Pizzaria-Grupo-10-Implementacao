package br.edu.ufersa.poo.pizzaria.view;

import br.edu.ufersa.poo.pizzaria.controller.DashboardController;
import br.edu.ufersa.poo.pizzaria.model.entities.Cargo;
import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioService;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public static void usuarios(){
        FXMLLoader fxmlLoader = new FXMLLoader(Tela.class.getResource("/br/edu/ufersa/poo/pizzaria/TelaUsuarios.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Usuário");
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
        stage.setTitle("Cadastro");
        stage.setScene(scene);
        stage.show();
    }
    public static void dashboard(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Tela.class.getResource("/br/edu/ufersa/poo/pizzaria/Dashboard.fxml"));
            Pane root = fxmlLoader.load();

            Scene scene = new Scene(root, 800, 600);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Pane loadSideBar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/edu/ufersa/poo/pizzaria/SideBar.fxml"));
        return loader.load();
    }

    public static void clientes() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Tela.class.getResource("/br/edu/ufersa/poo/pizzaria/TelaClientes.fxml"));
            Pane root = fxmlLoader.load();

            Scene scene = new Scene(root, 800, 600);
            stage.setTitle("Clientes");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[] ags){
        launch();
    }
}
