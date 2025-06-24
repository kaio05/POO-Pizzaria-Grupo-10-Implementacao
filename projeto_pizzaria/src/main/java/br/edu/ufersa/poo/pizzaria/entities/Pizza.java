package br.edu.ufersa.poo.pizzaria.entities;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tipo_pizza_id", nullable = false)
    private TipoPizza tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

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

}
