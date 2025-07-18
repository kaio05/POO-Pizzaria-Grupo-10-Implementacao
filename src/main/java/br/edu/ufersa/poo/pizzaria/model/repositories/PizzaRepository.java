package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.Pizza;
import java.util.List;
import java.util.UUID;

public interface PizzaRepository extends Repository<Pizza> {
 List<Pizza> findByClienteId(UUID clienteId);
 List<Pizza> findByType(UUID tipoId);
}