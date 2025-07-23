package br.edu.ufersa.poo.pizzaria.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ClienteRegisterController {
    @FXML private Label nome;
    @FXML private Label cpf;
    @FXML private Label endereco;
    @FXML private Label telefone;

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
}
