package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Pizza;
import java.util.List;
import java.util.UUID;

public interface PizzaRepository extends Repository<Pizza> {
 List<Pizza> findByClienteId(UUID clienteId);
 List<Pizza> findByType(UUID tipoId);
}