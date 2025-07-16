package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.TipoPizza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TipoPizzaRepositoryImpl extends RepositoryImpl<TipoPizza> implements TipoPizzaRepository {
    public TipoPizzaRepositoryImpl(Class<TipoPizza> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    @Override
    public TipoPizza findByCode(TipoPizza  tipoPizza) {
        TypedQuery<TipoPizza> query = em.createQuery("From TipoPizza where codigo = :code", TipoPizza.class);
        query.setParameter("code", tipoPizza.getCodigo());
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<TipoPizza> findAll() {return em.createQuery("from TipoPizza", TipoPizza.class).getResultList();}
}
