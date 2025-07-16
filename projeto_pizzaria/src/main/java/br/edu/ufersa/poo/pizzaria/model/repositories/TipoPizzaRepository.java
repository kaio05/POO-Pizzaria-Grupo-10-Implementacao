package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.TipoPizza;

public interface TipoPizzaRepository extends Repository<TipoPizza>{
    TipoPizza findByCode(String code);
}
