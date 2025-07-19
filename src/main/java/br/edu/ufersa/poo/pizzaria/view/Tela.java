package br.edu.ufersa.poo.pizzaria.view;

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
