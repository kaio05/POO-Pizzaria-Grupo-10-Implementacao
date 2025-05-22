package br.edu.ufersa.poo.pizzaria.entity;
import java.sql.Date;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;



public class Pedidos {
    private Cliente cliente;
    private Adicional adicional;
    private Pizza pizza;
    private Estado estado;
    private Tamanho tamanho;
    private Date data;

    public Cliente getCliente(){
        return cliente;
    }
    public void setCliente(Cliente cliente){
        if(cliente!=null){
            this.cliente = cliente;
        }
    }

    public Adicional getAdicional(){
        return adicional;
    }
    public void setCliente(Adicional adicional){
        if(adicional!=null){
            this.adicional = adicional;
        }
    }

    public Pizza getPizza(){
        return pizza;
    }
    public void setPizza(Pizza pizza){
        if(pizza!=null){
            this.pizza = pizza;
        }
    }

    public Estado getEstado(){
        return estado;
    }
    public void setEstado(Estado estado){
        if(estado!=null){
        this.estado = estado;
    }
   }

   public Tamanho getTamanho(){
        return tamanho;
   }
   public void setTamanho(Tamanho tamanho){
    if(tamanho!=null){
        this.tamanho = tamanho;
    }
   }

   public Date getData(){
        return data;
   }
   public void setData(Date data){
        if(data!=null){
            this.data = data;
        }
   }

   public Pedidos(Cliente cliente, Adicional adicional, Pizza pizza, Estado estado, Tamanho tamanho, Date data){
        this.cliente = cliente;
        this.adicional = adicional;
        this.pizza = pizza;
        this.estado = estado;
        this.tamanho = tamanho;
        this.data = data;
   }

   public static void cadastrar(Cliente cliente, Adicional adicional, Pizza pizza, String tamanhoStr, String estadoStr, Date data) {
        Tamanho tamanho = Tamanho.valueOf(tamanhoStr.toUpperCase());
        Estado estado = Estado.valueOf(estadoStr.toUpperCase());
        Pedidos pedido = new Pedidos(cliente, adicional, pizza, estado, tamanho, data);
        BancoFake.pedidos.add(pedido);
   }

     public static void editar(Cliente cliente, Adicional adicional, Pizza pizza, String tamanhoStr, String estadoStr) {
        for (Pedidos p : BancoFake.pedidos) {
            if (p.cliente.getNome().equals(cliente.getNome()) && p.pizza.equals(pizza)) {
                p.adicional = adicional;
                p.tamanho = Tamanho.valueOf(tamanhoStr.toUpperCase());
                p.estado = Estado.valueOf(estadoStr.toUpperCase());
                p.data = new Date(0);
                break;
            }
        }
    }

    public static void excluir(String nomeCliente) {
        BancoFake.pedidos.removeIf(p -> p.cliente.getNome().equals(nomeCliente));
    }

   public static Pedidos buscar(Cliente cliente, Pizza pizza, String estadoStr) {
        Estado estado = Estado.valueOf(estadoStr.toUpperCase());
        for (Pedidos p : BancoFake.pedidos) {
            if (p.cliente.getNome().equals(cliente.getNome()) &&
                p.pizza.equals(pizza) &&
                p.estado == estado) {
                return p;
            }
        }
        return new Pedidos(cliente, null, pizza, estado, null, null);
    }

     public static void gerarRelatorio(Cliente cliente, Pizza pizza) {
        System.out.println("Relatório de pedidos do cliente: " + cliente.getNome());
        for (Pedidos p : BancoFake.pedidos) {
            if (p.cliente.equals(cliente) && p.pizza.equals(pizza)) {
                System.out.println("Data: " + p.data +
                                   ", Adicional: " + p.adicional +
                                   ", Tamanho: " + p.tamanho +
                                   ", Estado: " + p.estado);
            }
        }
    }
}
