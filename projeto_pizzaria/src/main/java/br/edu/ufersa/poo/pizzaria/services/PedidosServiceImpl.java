package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.*;
import br.edu.ufersa.poo.pizzaria.repositories.PedidosRepository;

import java.util.List;
import java.util.UUID;

public class PedidosServiceImpl extends ServiceImpl<Pedidos> implements PedidosService {

    private final PedidosRepository pedidosRepo;

    public PedidosServiceImpl(PedidosRepository pedidosRepo) {
        super(pedidosRepo);
        this.pedidosRepo = pedidosRepo;
    }

    @Override
    public void register(Pedidos pedido) {
        if (pedido != null &&
                pedido.getCliente() != null &&
                pedido.getPizza() != null &&
                pedido.getAdicional() != null) {
            pedidosRepo.save(pedido);
        } else {
            System.out.println("Dados do pedido incompletos ou inválidos");
        }
    }

    @Override
    public void changeName(UUID id, String newName) {
        Pedidos pedido = pedidosRepo.findById(id);
        if (pedido != null && newName != null && !newName.isBlank()) {
            Cliente cliente = pedido.getCliente();
            cliente.setNome(newName);
            pedidosRepo.update(pedido);
        } else {
            System.out.println("Nome inválido ou pedido não encontrado");
        }
    }

    @Override
    public void changeAdicional(UUID id, Adicional newAdicional) {
        Pedidos pedido = pedidosRepo.findById(id);
        if (pedido != null && newAdicional != null) {
            pedido.setAdicional(newAdicional);
            pedidosRepo.update(pedido);
        } else {
            System.out.println("Adicional inválido ou pedido não encontrado");
        }
    }

    @Override
    public void changePizza(UUID id, Pizza newPizza) {
        Pedidos pedido = pedidosRepo.findById(id);
        if (pedido != null && newPizza != null) {
            pedido.setPizza(newPizza);
            pedidosRepo.update(pedido);
        } else {
            System.out.println("Pizza inválida ou pedido não encontrado");
        }
    }

    @Override
    public List<Pedidos> getByCliente(Cliente cliente) {
        if (cliente != null) {
            return pedidosRepo.findByCliente(cliente);
        }
        return List.of();
    }
}