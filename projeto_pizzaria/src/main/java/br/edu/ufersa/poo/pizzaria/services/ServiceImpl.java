package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.repositories.Repository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

public abstract class ServiceImpl<T> implements Service<T> {

    protected final Repository<T> repo;

    public ServiceImpl(Repository<T> repo) {
        this.repo = repo;
    }

    @Override
    public T getById(UUID id) {
        return repo.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repo.findAll();
    }

    @Override
    public void remove(UUID id) {
        T t = repo.findById(id);
        if(t != null) repo.delete(t);
    }

}
