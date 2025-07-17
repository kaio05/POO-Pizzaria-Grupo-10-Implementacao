package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.TipoPizza;

import java.util.UUID;

public interface TipoPizzaService extends Service<TipoPizza> {
    void changeName(TipoPizza tp, String newName);
    void changeValue(TipoPizza tp, double newValue);
    TipoPizza getByCode(TipoPizza tp);
}
