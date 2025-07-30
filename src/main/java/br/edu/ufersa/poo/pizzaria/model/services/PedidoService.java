package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.model.entities.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoService extends Service<Pedido> {
    void register(Pedido pedido);
    List<Pedido> getByCliente(Cliente cliente);

    void delete(UUID id);
}