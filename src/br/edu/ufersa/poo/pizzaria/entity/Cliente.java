package br.edu.ufersa.poo.pizzaria.entity;

import java.util.UUID;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;

public class Cliente {
    private UUID id;
    private String nome, endereco, cpf, telefone;

    public Cliente(String nome, String endereco, String cpf, String telefone) {
        this.id = UUID.randomUUID();
        setNome(nome);
        setEndereco(endereco);
        setCpf(cpf);
        setTelefone(telefone);
    }

    public Cliente(UUID id, String nome, String endereco, String cpf, String telefone) {
        setId(id);        
        setNome(nome);
        setEndereco(endereco);
        setCpf(cpf);
        setTelefone(telefone);
    }

    public void cadastrar(String nome, String endereco, String cpf, String telefone) {
        Cliente cliente = new Cliente(nome, endereco, cpf, telefone);
        if (BancoFake.exists(nome, endereco, cpf, telefone)) {
            throw new Error("Cliente já cadastrado!");
        }
        BancoFake.save(cliente);
    }

    public void editar(UUID id, String nome, String endereco, String cpf, String telefone) {
        Cliente cliente = new Cliente(id, nome, endereco, cpf, telefone);
        if (!BancoFake.exists(id)) {
            throw new Error("Cliente não existe!");
        }
        BancoFake.update(cliente);
    }

    public void excluir(UUID id) {
        if (!BancoFake.exists(id)) {
            throw new Error("Cliente não existe!");
        }
        BancoFake.remove(id);
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
