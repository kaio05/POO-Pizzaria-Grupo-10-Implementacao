package br.edu.ufersa.poo.pizzaria.model.repositories;
import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.model.entities.Pedido;

import java.util.List;

public interface PedidoRepository extends Repository<Pedido>{
    List<Pedido> findByCliente(Cliente cliente);
}