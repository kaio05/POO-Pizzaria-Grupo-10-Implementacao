package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Pedido;
import br.edu.ufersa.poo.pizzaria.entities.Pedido;
import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PedidoRepositoryImpl extends RepositoryImpl<Pedido> implements PedidoRepository {

    public PedidoRepositoryImpl(Class<Pedido> entityClass, EntityManager em) {
        super(entityClass, em);
    }
    @Override
    public List<Pedido> findByCliente(Cliente cliente) {
        return em.createQuery("FROM Pedido WHERE cliente = :cliente", Pedido.class)
                .setParameter("cliente", cliente)
                .getResultList();
    }

    @Override
    public List<Pedido> findAll() {
        return em.createQuery("from Pedido", Pedido.class).getResultList();
    }
}