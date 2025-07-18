package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.Pizza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class PizzaRepositoryImpl extends RepositoryImpl<Pizza> implements PizzaRepository {

    public PizzaRepositoryImpl(Class<Pizza> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    @Override
    public List<Pizza> findAll() {
        return em.createQuery(
                "from Pizza", Pizza.class)
                .getResultList();
    }

    @Override
    public List<Pizza> findByClienteId(UUID clienteId) {
        TypedQuery<Pizza> query = em.createQuery("From Pizza where cliente = :clienteId", Pizza.class);
        query.setParameter("clienteId", clienteId);
        return query.getResultList();
    }

    @Override
    public List<Pizza> findByType(UUID tipoId) {
        TypedQuery<Pizza> query = em.createQuery("From Pizza where tipo = :tipoId", Pizza.class);
        query.setParameter("tipoId", tipoId);
        return query.getResultList();
    }
}