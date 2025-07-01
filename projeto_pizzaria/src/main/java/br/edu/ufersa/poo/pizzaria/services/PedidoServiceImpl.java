package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.*;
import br.edu.ufersa.poo.pizzaria.repositories.PedidoRepository;
import br.edu.ufersa.poo.pizzaria.repositories.PedidoRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.repositories.PedidoRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.repositories.PedidoRepository;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;

import java.util.List;
import java.util.UUID;

public class PedidoServiceImpl extends ServiceImpl<Pedido> implements PedidoService {

    public PedidoServiceImpl(PedidoRepository repo) {
        super(new PedidoRepositoryImpl(Pedido.class, JPAUtil.getEntityManagerFactory()));
    }

    private final PedidoRepository repo = new PedidoRepositoryImpl(Pedido.class, JPAUtil.getEntityManagerFactory());

    @Override
    public void register(Pedido pedido) {
        repo.save(pedido);
    }

    @Override
    public void changeName(UUID id, String newName) {
        Pedido pedido = repo.findById(id);
        if (pedido != null && newName != null && !newName.isBlank()) {
            Cliente cliente = pedido.getCliente();
            cliente.setNome(newName);
            repo.update(pedido);
        } else {
            System.out.println("Nome inválido ou pedido não encontrado");
        }
    }

    @Override
    public void changeAdicionais(UUID id, List<Adicional> newAdicionais) {
        Pedido pedido = repo.findById(id);
        if (pedido != null && newAdicionais != null) {
            pedido.setAdicionais(newAdicionais);
            repo.update(pedido);
        } else {
            System.out.println("Pedido inválido ou pedido não encontrado");
        }
    }

    @Override
    public void changePizza(UUID id, Pizza newPizza) {
        Pedido pedido = repo.findById(id);
        if (pedido != null && newPizza != null) {
            pedido.setPizza(newPizza);
            repo.update(pedido);
        } else {
            System.out.println("Pizza inválida ou pedido não encontrado");
        }
    }

    @Override
    public List<Pedido> getByCliente(Cliente cliente) {
        if (cliente != null) {
            return repo.findByCliente(cliente);
        }
        return List.of();
    }
}