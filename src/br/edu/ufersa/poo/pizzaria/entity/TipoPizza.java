package br.edu.ufersa.poo.pizzaria.entity;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;

public class TipoPizza {
    private String nome;
    private double valor;

    // Construtor 
    public TipoPizza(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
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