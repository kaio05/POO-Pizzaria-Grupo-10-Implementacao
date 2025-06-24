package br.edu.ufersa.poo.pizzaria.repositories;
import br.edu.ufersa.poo.pizzaria.entities.Pedidos;
import java.util.List;

public interface PedidosRepository extends Repository<Pedidos>{
    Pedidos findByCliente(Cliente c)
}