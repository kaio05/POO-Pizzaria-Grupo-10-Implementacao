package br.edu.ufersa.poo.pizzaria.entity;

import java.util.UUID;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;

public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

    public Usuario(String nome, String senha) {
        setNome(nome);
        setSenha(senha);
    }

    public Usuario(UUID id, String nome, String email, String senha) {
        setId(id);
        setEmail(email);
        setNome(nome);
        setSenha(senha);
    }

    public Usuario(UUID id) {
        setId(id);
    }

    public Usuario() {}

    public void cadastrar(String nome, String email, String senha) {
        Usuario usuario = new Usuario(nome, email, senha);
        if (BancoFake.exists(usuario)) {
            throw new Error("Usuário já cadastrado! Tente fazer login");
        }
        usuario.id = UUID.randomUUID();
        BancoFake.save(usuario);
    }
    
    public void login(String nome, String senha) {
        Usuario usuario = new Usuario(nome, senha);
        if (!BancoFake.exists(usuario)) {
            throw new Error("Usuário não cadastrado! Cadastre-se!");
        }
        if (!BancoFake.authenticate(usuario)) {
            throw new Error("Senha incorreta!");
        }
        System.out.println("Autenticado com sucesso!");
    }
    
    public void editar(UUID id, String nome, String email, String senha) {
        Usuario usuario = new Usuario(id, nome, email, senha);
        if (!BancoFake.exists(usuario)) {
            throw new Error("Usuário não cadastrado! Cadastre-se!");
        }
        BancoFake.update(usuario);
    }

    public void excuir(UUID id) {
        Usuario usuario = new Usuario(id);
        if (!BancoFake.exists(usuario)) {
            throw new Error("Usuário não cadastrado! Cadastre-se!");
        }
        BancoFake.remove(usuario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
}
