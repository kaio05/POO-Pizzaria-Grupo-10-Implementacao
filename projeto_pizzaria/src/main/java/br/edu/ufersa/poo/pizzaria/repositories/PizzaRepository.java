package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Pizza;

public interface PizzaRepository extends Repository<Pizza> {
 List<Pizza> findByClienteId(UUID clienteId);