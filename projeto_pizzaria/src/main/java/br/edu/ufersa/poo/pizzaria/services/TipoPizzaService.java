package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.TipoPizza;

import java.util.UUID;

public interface TipoPizzaService extends Service<TipoPizza> {
    void changeName(UUID id, String newName);
    void changeValue(UUID id, double newValue);
    TipoPizza getByCode(String codigo);
}
