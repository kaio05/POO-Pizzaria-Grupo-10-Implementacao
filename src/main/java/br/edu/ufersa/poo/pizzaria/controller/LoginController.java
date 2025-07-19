package br.edu.ufersa.poo.pizzaria.controller;

import br.edu.ufersa.poo.pizzaria.exceptions.AuthenticationException;
import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioService;
import br.edu.ufersa.poo.pizzaria.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import br.edu.ufersa.poo.pizzaria.view.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javax.swing.*;

public class LoginController {
    @FXML private TextField userName;
    @FXML private PasswordField senha;
    @FXML private Label erro;
    UsuarioService service = new UsuarioServiceImpl(JPAUtil.getEntityManagerFactory());
    Usuario logante = new Usuario();

    @FXML public void autenticar (ActionEvent event){
        logante.setNome(userName.getText());
        logante.setSenha(senha.getText());
        try {
            service.login(logante);
            JOptionPane.showMessageDialog(null, "Autenticado com sucesso!");
            Tela.telaPrincipal();
        } catch (Exception e) {
            erro.setText(e.getMessage());
            erro.setTextFill(Color.RED);
            erro.setVisible(true);
        }
    }
    @FXML public void cadastrar (ActionEvent event){
        Tela.telaCadastro();
    }
}
