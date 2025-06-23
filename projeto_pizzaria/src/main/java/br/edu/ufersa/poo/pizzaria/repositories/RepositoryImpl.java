package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public abstract class RepositoryImpl<T> implements Repository<T> {

    protected final EntityManager em;
    private final Class<T> entityClass;

    public RepositoryImpl(Class<T> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }

    @Override
    public T findById(UUID id) {
        return em.find(entityClass, id);
    }

    @Override
    public void save(T t) {
        EntityTransaction ts = em.getTransaction();
        try {
            ts.begin();
            em.persist(t);
            ts.commit();
        } catch (RuntimeException e) {
            if(ts.isActive()) ts.rollback();
            throw new RuntimeException("Erro ao salvar usuário", e);
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
