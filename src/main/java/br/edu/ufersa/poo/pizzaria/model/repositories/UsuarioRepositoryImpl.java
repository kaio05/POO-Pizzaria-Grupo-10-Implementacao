package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.Cargo;
import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioRepositoryImpl extends RepositoryImpl<Usuario> implements UsuarioRepository {

    public UsuarioRepositoryImpl(Class<Usuario> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    @Override
    public List<Usuario> findAll() {
        em.clear();
        return em.createQuery("from Usuario", Usuario.class).getResultList();
    }

    @Override
    public Usuario findByEmail(Usuario usuario) {
        TypedQuery<Usuario> query = em.createQuery("from Usuario where email = :email", Usuario.class);
        query.setParameter("email", usuario.getEmail());
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public boolean findAdmin(Usuario usuario) {
        TypedQuery<Usuario> query = em.createQuery("from Usuario where cargo = :cargo", Usuario.class);
        query.setParameter("cargo", usuario.getCargo());
        return query.getSingleResultOrNull() != null;
    }
}
