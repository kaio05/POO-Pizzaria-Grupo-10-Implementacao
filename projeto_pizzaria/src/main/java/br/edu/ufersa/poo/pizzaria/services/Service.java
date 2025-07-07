package br.edu.ufersa.poo.pizzaria.services;

import java.util.List;

public interface Service<T> {
    T getById(T t);
    List<T> getAll();
    void register(T t);
    void update(T t);
    void remove(T t);
}
