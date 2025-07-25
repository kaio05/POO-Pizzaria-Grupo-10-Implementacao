package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.Cargo;
import br.edu.ufersa.poo.pizzaria.utils.Session;
import br.edu.ufersa.poo.pizzaria.view.Tela;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SideBarController {
    @FXML private Label usuario;
    @FXML private Label cargo;
    @FXML private Button buttonUsuarios;
    @FXML private Button buttonSair;

    @FXML public void initialize() {
        Cargo sessionCargo = Session.getUsuario().getCargo();
        usuario.setText(Session.getUsuario().getNome());
        usuario.setAlignment(Pos.CENTER);
        cargo.setText(sessionCargo == Cargo.ADMIN? "Admin" : "Funcionário");
        cargo.setAlignment(Pos.CENTER);
        buttonUsuarios.setVisible(sessionCargo == Cargo.ADMIN);
        buttonSair.setLayoutY(sessionCargo == Cargo.ADMIN? 540:493);
    }

    @FXML public void sair() {
        Tela.telaLogin();
    }
    @FXML public void nav_home() {
        Tela.dashboard();
    }
    @FXML public void nav_pedidos() {
        System.out.println("pedidos");
    }
    @FXML public void nav_clientes() {
        Tela.clientes();
    }
    @FXML public void nav_sabores() {
        System.out.println("sabores");
    }
    @FXML public void nav_pizzas() {
        System.out.println("pizzas");
    }
    @FXML public void nav_adicionais() {
        System.out.println("adicionais");
    }
    @FXML public void nav_usuarios() {
        Tela.usuarios();
    }
}
