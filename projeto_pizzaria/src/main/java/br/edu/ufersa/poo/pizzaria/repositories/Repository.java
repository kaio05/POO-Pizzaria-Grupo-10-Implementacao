package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {
    T findById(UUID id);
    List<T> findAll();
    void save(T t);
    T update(T t);
    void delete(T t);
}
