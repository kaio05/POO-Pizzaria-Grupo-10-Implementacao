package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.Pizza;

import java.util.List;
import java.util.UUID;

public interface PizzaService extends Service<Pizza> {
    List<Pizza> getByClientId(UUID clienteId);
    List<Pizza> getByType(UUID tipoPizzaId);
}