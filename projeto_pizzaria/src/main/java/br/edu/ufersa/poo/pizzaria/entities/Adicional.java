package br.edu.ufersa.poo.pizzaria.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "adicionais")
public class Adicional implements Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private double valor;

    // Construtor
    public Adicional(){}

    @Override
    public String toString() {
        return "Adicional{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }

    public Adicional(String codigo, String nome, double valor) {
        setCodigo(codigo);
        setNome(nome);
        setValor(valor);
    }

    //  Get e Set codigo
    public void setCodigo(String s){
        if (s != null){
            this.codigo = s;
        }
    }
    public String getCodigo() { 
        return codigo; 
    }
    //  Get e Set nome
    public void setNome(String n){
        if (n != null){
            this.nome = n;
        }
    }
    public String getNome() { 
        return nome;
    }
    //  Get e Set valor
    public void setValor(double d){
        if (d > 0){
            this.valor = d;
        }
    }
    public double getValor() {return valor;}
    //  Get Id
    public UUID getId() {
        return id;
    }
}