package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.entities.Pedido;
import br.edu.ufersa.poo.pizzaria.entities.Pizza;

import java.util.List;
import java.util.UUID;

public interface PedidoService extends Service<Pedido> {
    void register(Pedido pedido);
    List<Pedido> getByCliente(Cliente cliente);
}