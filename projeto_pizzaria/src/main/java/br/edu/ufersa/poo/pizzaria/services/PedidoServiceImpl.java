package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.*;
import br.edu.ufersa.poo.pizzaria.repositories.PedidoRepository;
import br.edu.ufersa.poo.pizzaria.repositories.PedidoRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.repositories.PedidoRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.repositories.PedidoRepository;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

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