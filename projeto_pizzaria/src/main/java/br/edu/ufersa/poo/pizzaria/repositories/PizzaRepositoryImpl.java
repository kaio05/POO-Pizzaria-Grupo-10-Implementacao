package br.edu.ufersa.poo.pizzaria.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public abstract class RepositoryImpl<Pizza> implements Repository<Pizza> {

    protected final EntityManager em;
    private final Class<Pizza> entityClass;

    public RepositoryImpl(Class<T> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    @Override
    public T findById(UUID id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public void save(T t) {
        EntityTransaction ts = em.getTransaction();
        try {
            ts.begin();
            em.persist(t);
            ts.commit();
        } catch (RuntimeException e) {
            if (ts.isActive()) ts.rollback();
            throw new RuntimeException("Erro ao salvar entidade", e);
        }
    }

    @Override
    public T update(T t) {
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        T merged = em.merge(t);
        ts.commit();
        return merged;
    }

    @Override
    public void delete(T t) {
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        em.remove(em.contains(t) ? t : em.merge(t));
        ts.commit();
    }
}