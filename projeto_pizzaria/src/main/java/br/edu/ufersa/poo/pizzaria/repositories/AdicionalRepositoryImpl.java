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

    public List<Adicional> findAll() {
        return em.createQuery("from Adicional", Adicional.class).getResultList();
    }

    @Override
    public Adicional findByCode(String code) {
        TypedQuery<Adicional> query = em.createQuery("From Adicional where codigo = :code", Adicional.class);
        query.setParameter("code", code);
        return query.getResultStream().findFirst().orElse(null);
    }
}
