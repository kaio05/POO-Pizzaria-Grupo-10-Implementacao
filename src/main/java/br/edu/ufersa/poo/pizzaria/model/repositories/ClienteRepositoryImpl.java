package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ClienteRepositoryImpl extends RepositoryImpl<Cliente> implements ClienteRepository {

    public ClienteRepositoryImpl(Class<Cliente> entityClass, EntityManager em) {
        super(entityClass, em);
    }

    @Override
    public Cliente findByCpf(Cliente cliente) {
        TypedQuery<Cliente> query = em.createQuery("from Cliente where cpf = :cpf", Cliente.class);
        query.setParameter("cpf", cliente.getCpf());
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente", Cliente.class).getResultList();
    }

}
