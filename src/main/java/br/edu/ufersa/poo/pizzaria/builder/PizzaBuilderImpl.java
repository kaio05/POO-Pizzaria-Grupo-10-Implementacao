package br.edu.ufersa.poo.pizzaria.builder;

import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.model.entities.Pizza;
import br.edu.ufersa.poo.pizzaria.model.entities.TipoPizza;
import java.util.UUID;

public class PizzaBuilderImpl implements PizzaBuilder {
    private UUID id;
    private TipoPizza tipo;
    private Cliente cliente;

    // Construtor do Builder - pode inicializar campos padrão se houver
    public PizzaBuilderImpl() {
        // Gera um ID padrão ao criar o builder, pode ser sobrescrito com withId()
        this.id = UUID.randomUUID();
    }

    @Override
    public PizzaBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public PizzaBuilder withTipo(TipoPizza tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de pizza não pode ser nulo.");
        }
        this.tipo = tipo;
        return this;
    }

    @Override
    public PizzaBuilder withCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        this.cliente = cliente;
        return this;
    }

    @Override
    public Pizza build() {
        // Validações antes de construir o objeto Pizza
        if (this.tipo == null) {
            throw new IllegalStateException("Tipo de pizza é obrigatório para construir uma Pizza.");
        }
        if (this.cliente == null) {
            throw new IllegalStateException("Cliente é obrigatório para construir uma Pizza.");
        }

        // Usa o construtor privado da entidade Pizza
        return new Pizza(this);
    }
}
