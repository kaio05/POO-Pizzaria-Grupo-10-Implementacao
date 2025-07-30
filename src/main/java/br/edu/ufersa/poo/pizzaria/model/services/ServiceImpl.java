package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.Entidade;
import br.edu.ufersa.poo.pizzaria.model.repositories.Repository;

import java.util.List;

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
