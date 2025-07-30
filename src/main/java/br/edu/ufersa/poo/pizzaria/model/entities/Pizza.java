package br.edu.ufersa.poo.pizzaria.model.entities;
import br.edu.ufersa.poo.pizzaria.builder.PizzaBuilderImpl;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="pizzas")
public class Pizza implements Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tipo_pizza_id", nullable = false)
    private TipoPizza tipo;

    @ManyToOne
    @JoinColumn(name = "clientes_id", nullable = false)
    private Cliente cliente;

    public Pizza(PizzaBuilderImpl pizzaBuilder) {
    }

    public TipoPizza getPizza() {return this.tipo;}

    public void setTipo(TipoPizza tipo){
        if(tipo!=null){
            this.tipo = tipo;
        }else{
            System.out.println("Tipo não encontrado");
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

    public Pizza(TipoPizza tipo, Cliente cliente){
        setTipo(tipo);
        setCliente(cliente);
    }

   public Pizza(){}

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", cliente=" + cliente +
                '}';
    }


}
