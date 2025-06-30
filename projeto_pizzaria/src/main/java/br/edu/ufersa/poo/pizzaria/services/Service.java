package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface Service<T> {
    T getById(T t);
    List<T> getAll();
    void register(T t);
    void update(T t);
    void remove(T t);
}
