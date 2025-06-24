package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.entities.Pedidos;

import java.util.List;

public interface PedidosService extends Service<Pedidos> {
    void changeName(UUID id, String newName);
    void changeAdicional(UUID id, Adicional newAdicional);
    void changePizza(UUID id, Pizza newPizza);
    void register(Pedidos pedido);
    List<Pedidos> getByCliente(Cliente cliente);
}