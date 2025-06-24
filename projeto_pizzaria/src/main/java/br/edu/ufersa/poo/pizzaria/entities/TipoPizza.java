package br.edu.ufersa.poo.pizzaria.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tipos_pizza")
public class TipoPizza {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private double valor;

    //  Get Id
    public UUID getId() {
        return id;
    }
    //  Get e Set nome
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        if(nome!= null){
            this.nome = nome;
        }
    }
    //  Get e Set valor
    public double getValor(){
        return valor;
    }
    public void setValor(double valor){
        if(valor > 0){
            this.valor = valor;
        }
    }
    // Construtor
    public TipoPizza(){}
    public TipoPizza(String codigo, String nome, double valor) {
        setCodigo(codigo);
        setNome(nome);
        setValor(valor);
    }

    @Override
    public String toString() {
        return "TipoPizza{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}