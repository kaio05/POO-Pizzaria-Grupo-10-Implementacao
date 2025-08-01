package br.edu.ufersa.poo.pizzaria.builder;

import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.model.entities.Pizza;
import br.edu.ufersa.poo.pizzaria.model.entities.TipoPizza;
import java.util.UUID;

public interface PizzaBuilder {
    PizzaBuilder withId(UUID id);
    PizzaBuilder withTipo(TipoPizza tipo);
    PizzaBuilder withCliente(Cliente cliente);
    Pizza build();
}
