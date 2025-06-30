package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Entidade;
import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.repositories.Repository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

public abstract class ServiceImpl<T extends Entidade> implements Service<T> {

    protected final Repository<T> repo;

    public ServiceImpl(Repository<T> repo) {
        this.repo = repo;
    }

    @Override
    public T getById(T t) {
        return repo.findById(t);
    }

    @Override
    public List<T> getAll() {
        return repo.findAll();
    }

    @Override
    public void remove(T t) {
        T tExists = repo.findById(t);
        if(tExists != null) repo.delete(t);
    }

}
