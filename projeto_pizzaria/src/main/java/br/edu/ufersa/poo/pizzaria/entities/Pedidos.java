package br.edu.ufersa.poo.pizzaria.entities;
import java.sql.Date;
import java.util.UUID;
import jakarta.persistence.*

import br.edu.ufersa.poo.pizzaria.db.BancoFake;


@Entity
@Table(name = "Pedidos")

public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    public class Pedidos(){}
    @Column(nullable = false)
    private Cliente cliente;
    @Column(nullable = false)
    private Adicional adicional;
    Column(nullable = false)
    private Pizza pizza;
    Column(nullable = false)
    private Estado estado;
    Column(nullable = false)
    private Tamanho tamanho;
    Column(nullable = false)
    private Date data;

    public Cliente getCliente(){
        return cliente;
    }
    public void setCliente(Cliente cliente){
        if(cliente!=null){
            this.cliente = cliente;
        }else{
            System.out.println("Cliente não encontrado");
        }
    }

    public Adicional getAdicional(){
        return adicional;
    }
    public void setAdicional(Adicional adicional){
        if(adicional!=null){
            this.adicional = adicional;
        }else{
            System.out.println("Adicional não encontrado");
        }
    }

    public Pizza getPizza(){
        return pizza;
    }
    public void setPizza(Pizza pizza){
        if(pizza!=null){
            this.pizza = pizza;
        }else{
            System.out.println("Pizza não encontrada");
        }
    }

    public Estado getEstado(){
        return estado;
    }
    public void setEstado(Estado estado){
        if(estado!=null){
        this.estado = estado;
    }else{
            System.out.println("Estado não encontrado");
        }
   }

   public Tamanho getTamanho(){
        return tamanho;
   }
   public void setTamanho(Tamanho tamanho){
    if(tamanho!=null){
        this.tamanho = tamanho;
    }else{
        System.out.println("Tamanho não encontrado");
    }
   }

   public Date getData(){
        return data;
   }
   public void setData(Date data){
        if(data!=null){
            this.data = data;
        }else{
            System.out.println("Data não encontrada");
        }
   }

   public Pedidos(Cliente cliente, Adicional adicional, Pizza pizza, Estado estado, Tamanho tamanho, Date data){
        setCliente(cliente);
        setAdicional(adicional);
        setPizza(pizza);
        setEstado(estado);
        setTamanho(tamanho);
        setData(data);
   }

   public String toString(){
        return "Pedido: " +
                "Cliente= "+"|"+
                "Adicional= "+"|"+
                "Pizza= "+"|"+
                "Estado= "+"|"+
                "Tamanho= "+"|"+
                "Data= ";
   }




}
