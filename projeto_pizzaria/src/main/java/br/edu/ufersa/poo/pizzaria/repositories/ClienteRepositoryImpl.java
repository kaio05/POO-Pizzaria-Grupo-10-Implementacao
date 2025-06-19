package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class ClienteRepositoryImpl extends RepositoryImpl<Cliente> implements ClienteRepository {

    public ClienteRepositoryImpl(Class<Cliente> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    @Override
    public Cliente findByCpf(String cpf) {
        TypedQuery<Cliente> query = em.createQuery("from Cliente where cpf = :cpf", Cliente.class);
        query.setParameter("cpf", cpf);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente", Cliente.class).getResultList();
    }

}
