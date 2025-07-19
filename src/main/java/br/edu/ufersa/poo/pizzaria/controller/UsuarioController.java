package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioService;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import br.edu.ufersa.poo.pizzaria.view.Tela;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javax.swing.*;

public class UsuarioController {
    @FXML private TextField userName;
    @FXML private PasswordField senha;
    @FXML private TextField email;
    @FXML private Label erro;

    UsuarioService service = new UsuarioServiceImpl(JPAUtil.getEntityManagerFactory());
    Usuario novoUsu = new Usuario();
    @FXML public void retornar(){
        Tela.telaLogin();
    }
    @FXML public void cadastrar(){
        novoUsu.setNome(userName.getText());
        novoUsu.setEmail(email.getText());
        novoUsu.setSenha(senha.getText());
        try {
            service.register(novoUsu);
            JOptionPane.showConfirmDialog(null, "Usuário cadastrado com sucesso!");
            Tela.telaLogin();
        } catch (Exception e) {
            erro.setText(e.getMessage());
            erro.setTextFill(Color.RED);
            erro.setVisible(true);
        }

    }
}
