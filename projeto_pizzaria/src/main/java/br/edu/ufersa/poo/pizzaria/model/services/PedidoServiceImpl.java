package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.*;
import br.edu.ufersa.poo.pizzaria.model.repositories.PedidoRepository;
import br.edu.ufersa.poo.pizzaria.model.repositories.PedidoRepositoryImpl;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PedidoServiceImpl extends ServiceImpl<Pedido> implements PedidoService {

    private final PedidoRepository repo;

    public PedidoServiceImpl(EntityManager em) {
        super(new PedidoRepositoryImpl(Pedido.class, em));
        this.repo = new PedidoRepositoryImpl(Pedido.class, em);
    }

    @Override
    public void register(Pedido pedido) {
        repo.save(pedido);
    }

    @Override
    public void update(Pedido pedido) {
        System.out.println("A implementar");
    }

    @Override
    public List<Pedido> getByCliente(Cliente cliente) {
        if (cliente != null) {
            return repo.findByCliente(cliente);
        }
        return List.of();
    }
}