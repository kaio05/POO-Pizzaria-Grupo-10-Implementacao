package br.edu.ufersa.poo.pizzaria.entities;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;

public class TipoPizza {
    private String nome;
    private double valor;

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
    public TipoPizza(String nome, double valor) {
        this.setNome(nome);
        this.setValor(valor);
    }

    // Cadastrar
    public static void cadastrar(String nome, double valor) {
        BancoFake.tiposPizza.add(new TipoPizza(nome, valor));
    }

    // Editar
    public static void editar(String nomeAntigo, String novoNome, double novoValor) {
        for (TipoPizza tipo : BancoFake.tiposPizza) {
            if (tipo.nome.equals(nomeAntigo)) {
                tipo.nome = novoNome;
                tipo.valor = novoValor;
                break;
            }
        }
    }

    // Excluir
    public static void excluir(String nome) {
        BancoFake.tiposPizza.removeIf(tipo -> tipo.nome.equals(nome));
    }
}