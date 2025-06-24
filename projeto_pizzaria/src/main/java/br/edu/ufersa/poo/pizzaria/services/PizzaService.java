package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Pizza;
import java.util.UUID;

public interface PizzaService extends Service<Pizza> {
    List<Pizza> buscarPorCliente(UUID clienteId);
    List<Pizza> buscarPorTipo(UUID tipoPizzaId);