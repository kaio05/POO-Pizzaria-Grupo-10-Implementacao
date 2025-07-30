package br.edu.ufersa.poo.pizzaria.model.repositories;

import java.util.List;

public interface Repository<T> {
    T findById(T t);
    List<T> findAll();
    void save(T t);
    T update(T t);
    void delete(T t);
}
