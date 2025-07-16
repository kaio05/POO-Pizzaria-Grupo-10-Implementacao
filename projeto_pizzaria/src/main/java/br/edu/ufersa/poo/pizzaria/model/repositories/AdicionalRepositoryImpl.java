package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.Adicional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

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
