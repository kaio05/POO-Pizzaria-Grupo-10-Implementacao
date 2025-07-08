package br.edu.ufersa.poo.pizzaria.entities;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;


@Entity
@Table(name = "Pedidos")

public class Pedido implements Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="cliente_id",nullable = false)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "pedido_adicional",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "adicional_id")
    )
    private List<Adicional> adicionais = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pizza_id") // Cria a FK em Pedido
    private Pizza pizza;

    @Column(nullable = false)
    private Estado estado;

    @Column(nullable = false)
    private Tamanho tamanho;

    @Column(nullable = false)
    private Date data;

    public Cliente getCliente(){
        return cliente;
    }
    public void setCliente(Cliente cliente){
        if(cliente!=null){
            this.cliente = cliente;
        }else{
            System.out.println("Cliente não pode ser nulo");
        }
    }

    public List<Adicional> getAdicional(){
        return this.adicionais;
    }
    public void setAdicionais(List<Adicional> adicionais){
        this.adicionais = adicionais;
    }

    public Pizza getPizza(){
        return pizza;
    }
    public void setPizza(Pizza pizza){
        if(pizza!=null){
            this.pizza = pizza;
        }else{
            System.out.println("Pizza não pode ser nula");
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

   public Pedido(Cliente cliente, List<Adicional> adicionais, Pizza pizza, Estado estado, Tamanho tamanho, Date data){
        setCliente(cliente);
        setAdicionais(adicionais);
        setPizza(pizza);
        setEstado(estado);
        setTamanho(tamanho);
        setData(data);
   }

    public Pedido() {
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", adicionais=" + adicionais +
                ", pizza=" + pizza +
                ", estado=" + estado +
                ", tamanho=" + tamanho +
                ", data=" + data +
                '}';
    }

    @Override
    public UUID getId() {
        return this.id;
    }
}
