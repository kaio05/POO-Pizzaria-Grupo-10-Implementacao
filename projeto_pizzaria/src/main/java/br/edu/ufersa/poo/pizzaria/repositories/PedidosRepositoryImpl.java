package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Pedidos;
import jakarta.persistence.EntityManager;

public class PedidosRepositoryImpl extends RepositoryImpl<Pedidos> implements PedidosRepository {

    public PedidosRepositoryImpl(EntityManager em) {
        super(Pedidos.class, em);
    }
    @Override
    public List<Pedidos> findByCliente(Cliente cliente) {
        return em.createQuery("SELECT p FROM Pedidos p WHERE p.cliente = :cliente", Pedidos.class)
                .setParameter("cliente", cliente)
                .getResultList();
    }
}