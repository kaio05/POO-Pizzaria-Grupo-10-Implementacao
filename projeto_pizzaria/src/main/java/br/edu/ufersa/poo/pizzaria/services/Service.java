package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface Service<T> {
    T getById(UUID id);
    List<T> getAll();
    void register(T t);
    void remove(UUID id);
}
