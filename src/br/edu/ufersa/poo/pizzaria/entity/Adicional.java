package br.edu.ufersa.poo.pizzaria.entity;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;

public class Adicional {
    // Atributos
    private String codigo;
    private String nome;
    private double valor;

    // Construtor 
    public Adicional(String codigo, String nome, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
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
        Adicional adicional = buscarPorCodigo(codigo);
        // Procura o adicional pelo código
        if (adicional != null) {
            adicional.nome = novoNome;
            adicional.valor = novoValor;
        } else {
            System.out.println("Adicional não encontrado!");
        }
        // Se o adicional existir ele vai editar, caso não retorna mensagem de erro
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

    //Getters
    public String getCodigo() { 
        return codigo; 
    }
    public String getNome() { 
        return nome;
    }
    public double getValor() { 
        return valor;
    }
}