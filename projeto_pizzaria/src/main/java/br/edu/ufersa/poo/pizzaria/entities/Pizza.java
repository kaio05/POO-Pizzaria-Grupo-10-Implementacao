package br.edu.ufersa.poo.pizzaria.entities;
import jakarta.persistence.*;

import br.edu.ufersa.poo.pizzaria.db.BancoFake;
@Entity
@Table(name="pizza");
public class Pizza {
    @ManyToOne
    @JoinColumns(name = "tipo_pizza_id", nullable = false)
    private TipoPizza pizza;
    @ManyToOne
    @JoinColumns(name = "clientes_id", nullable = false)
    private Cliente cliente;

    public TipoPizza getPizza(){

        return pizza;
    }

    public void setPizza(TipoPizza pizza){
        if(pizza!=null){
            this.pizza = pizza;
        }else{
            System.out.println("Pizza não encontrada");
        }
    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        if(cliente != null){
            this.cliente = cliente;
        }else {
            System.out.println("Cliente não encontrado");
        }
    }

    public Pizza(TipoPizza pizza, Cliente cliente){
        setPizza(pizza);
        setCliente(cliente);
        BancoFake.pizzas.add(this);
    
        
    }

   public Pizza(){}

}
