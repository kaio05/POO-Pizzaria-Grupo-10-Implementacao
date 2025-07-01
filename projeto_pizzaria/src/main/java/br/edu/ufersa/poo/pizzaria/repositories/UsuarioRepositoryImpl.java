package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

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
}
