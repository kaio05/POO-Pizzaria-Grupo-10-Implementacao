package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final EntityManager em = JPAUtil.getEntityManagerFactory();

    @Override
    public Usuario findById(Usuario usuario) {
        return em.find(Usuario.class, usuario.getId());
    }

    @Override
    public List<Usuario> findAll() {
        return em.createQuery("from Usuario", Usuario.class).getResultList();
    }

    @Override
    public void save(Usuario usuario) {
        EntityTransaction ts = em.getTransaction();
        try {
            ts.begin();
            em.persist(usuario);
            ts.commit();
        } catch (RuntimeException e) {
            if(ts.isActive()) ts.rollback();
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
    }

    @Override
    public Usuario update(Usuario usuario) {
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        Usuario merged = em.merge(usuario);
        ts.commit();
        return merged;
    }

    @Override
    public void delete(Usuario usuario) {
        EntityTransaction ts = em.getTransaction();
        ts.begin();
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
        ts.commit();
    }

    @Override
    public Usuario findByEmail(Usuario usuario) {
        TypedQuery<Usuario> query = em.createQuery("from Usuario where email = :email", Usuario.class);
        query.setParameter("email", usuario.getEmail());
        return query.getResultStream().findFirst().orElse(null);
    }
}
