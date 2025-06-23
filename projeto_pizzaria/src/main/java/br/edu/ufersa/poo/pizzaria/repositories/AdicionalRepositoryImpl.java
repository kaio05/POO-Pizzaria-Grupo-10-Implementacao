package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class AdicionalRepositoryImpl extends RepositoryImpl<Adicional> implements AdicionalRepository {
    public AdicionalRepositoryImpl(Class<Adicional> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    public Adicional findByCpf(String cod) {
        TypedQuery<Adicional> query = em.createQuery("from Adicional where cod = :cod", Adicional.class);
        query.setParameter("cod", cod);
        return query.getResultStream().findFirst().orElse(null);
    }

    public List<Adicional> findAll() {
        return em.createQuery("from Cliente", Adicional.class).getResultList();
    }

    @Override
    public Adicional findByCod(String cod) {
        TypedQuery<Adicional> query = em.createQuery("From Adicional where cod = :cod", Adicional.class);
        query.setParameter("Cod", cod);
        return query.getResultStream().findFirst().orElse(null);
    }
}
