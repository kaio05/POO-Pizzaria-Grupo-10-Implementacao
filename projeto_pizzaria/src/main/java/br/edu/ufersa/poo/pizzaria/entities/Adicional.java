package br.edu.ufersa.poo.pizzaria.entities;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;

public class Adicional {
    // Atributos
    private String codigo;
    private String nome;
    private double valor;

    // Construtor 
    public Adicional(String codigo, String nome, double valor) {
        this.setCodigo(codigo);
        this.setNome(nome);
        this.setValor(valor);
    }

    // Cadastrar
    public static void cadastrar(String codigo, String nome, double valor) {
        validarCampos(codigo, nome, valor); 
        // Valida os dados
        verificarCodigoRepetido(codigo); 
        // Verifica se o código já existe
        Adicional novo = new Adicional(codigo, nome, valor);
        BancoFake.adicionais.add(novo);
        // Cria e salva o adicional
    }

    // Editar
    public static void editar(String codigo, String novoNome, double novoValor) {
        validarCampos(codigo, novoNome, novoValor); 
        // Valida os novos dados
        for (Adicional adic : BancoFake.adicionais) {
            if (adic.codigo.equals(codigo)) {
                adic.nome = novoNome;
                adic.valor = novoValor;
                break;
            }else {
            System.out.println("Adicional não encontrado!");
            }
        }
        // Se o adicional existir, checo a existencia a partir do codigo, caso o codigo colocado pelo usuario seja igual a algum dos codigos existentes, ele vai editar, caso não retorna mensagem de erro
    }

    // Excluir
    public static void excluir(String codigo) {
        Adicional adicional = buscarPorCodigo(codigo);
        // Procura o adicional na lista
        if (adicional != null) {
            BancoFake.adicionais.remove(adicional);
            System.out.println("Adicional removido!");
        } else {
            System.out.println("Adicional não encontrado!");
        }
        // Se o adicional existir nós excluimos ele da lista feita no banco fake caso não exista retornamos mensagem de erro
    }

    // Buscar
    public static Adicional buscarPorCodigo(String codigo) {
        for (Adicional adicional : BancoFake.adicionais) {
            // Percorre a lista manualmente
            if (adicional.codigo.equals(codigo)) {
                return adicional;
                // Se o codigo passado na lista for igual ao codigo digitado retornamos o adicional (o que esta sendo procurado no caso)
            }
        }
        return null;
    }

    // Validações e Verificações
    private static void validarCampos(String codigo, String nome, double valor) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("Código inválido!");
        }
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo!");
        }
    }

    private static void verificarCodigoRepetido(String codigo) {
        for (Adicional adicional : BancoFake.adicionais) {
            if (adicional.codigo.equals(codigo)) {
                throw new IllegalArgumentException("Código já cadastrado!");
            }
        }
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}