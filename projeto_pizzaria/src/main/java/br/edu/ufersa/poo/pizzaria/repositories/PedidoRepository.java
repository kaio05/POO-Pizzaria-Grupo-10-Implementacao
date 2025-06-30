package br.edu.ufersa.poo.pizzaria.repositories;
import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.entities.Pedido;

import java.util.List;

public interface PedidoRepository extends Repository<Pedido>{
    List<Pedido> findByCliente(Cliente cliente);
}