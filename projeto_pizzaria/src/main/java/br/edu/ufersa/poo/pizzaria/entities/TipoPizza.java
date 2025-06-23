package br.edu.ufersa.poo.pizzaria.entities;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public class TipoPizza {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
    public TipoPizza(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }
}