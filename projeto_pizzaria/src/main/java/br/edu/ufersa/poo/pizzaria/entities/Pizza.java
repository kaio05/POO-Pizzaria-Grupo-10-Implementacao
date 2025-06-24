package br.edu.ufersa.poo.pizzaria.entities;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;

public class Pizza {
    private TipoPizza pizza;
    private Cliente cliente;

    public TipoPizza getPizza(){

        return pizza;
    }

    public void setPizza(TipoPizza pizza){
        if(pizza!=null){
            this.pizza = pizza;
        }
    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        if(cliente != null){
            this.cliente = cliente;
        }
    }

    public Pizza(TipoPizza pizza, Cliente cliente){
        setPizza(pizza);
        setCliente(cliente);
        BancoFake.pizzas.add(this);
    
        
    }

    public Pizza buscarPizza(TipoPizza tipo, Cliente c){
        for(Pizza p : BancoFake.pizzas){
            if(p.getPizza().getNome().equals(pizza.getNome()) && p.getCliente().getNome().equals(cliente.getNome())){
                return p;
            }
        else{
            System.out.println("Não encontrado!");
        }
        }
        return new Pizza(tipo, cliente);
    }


}
