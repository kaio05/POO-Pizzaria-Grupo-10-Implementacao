package br.edu.ufersa.poo.pizzaria.model.entities;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario implements Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    public Usuario() {}

    public Usuario(String nome, String email, String senha) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.isEmpty())
            System.out.println("Nome não pode ser vazio");
        else
            this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email == null || email.isEmpty())
            System.out.println("Email não pode ser vazio");
        else
            this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if(senha == null || senha.isEmpty())
            System.out.println("Senha não pode ser vazio");
        else
            this.senha = senha;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
