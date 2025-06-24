package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Pizza;
import br.edu.ufersa.poo.pizzaria.repositories.PizzaRepository;
import java.util.List;
import java.util.UUID;

public class PizzaServiceImpl extends ServiceImpl<Pizza> implements PizzaService {

    private final PizzaRepository pizzaRepo;

    public PizzaServiceImpl(PizzaRepository pizzaRepo) {
        super(pizzaRepo);
        this.pizzaRepo = pizzaRepo;
    }

    @Override
    public List<Pizza> buscarPorCliente(UUID clienteId) {
        if (clienteId == null) {
            throw new IllegalArgumentException("ID do cliente não pode ser nulo");
        }
        return pizzaRepo.findByClienteId(clienteId);
    }

    @Override
    public List<Pizza> buscarPorTipo(UUID tipoPizzaId) {
        if (tipoPizzaId == null) {
            throw new IllegalArgumentException("ID do tipo de pizza não pode ser nulo");
        }
        return pizzaRepo.findByTipoPizzaId(tipoPizzaId);
    }
    
    @Override
    public void register(Pizza pizza) {
        if (pizza == null) {
            throw new IllegalArgumentException("Pizza não pode ser nula");
        }
        if (pizza.getTipoPizza() == null) {
            throw new IllegalArgumentException("Pizza deve ter um tipo associado");
        }
        if (pizza.getCliente() == null) {
            throw new IllegalArgumentException("Pizza deve ter um cliente associado");
        }
        super.register(pizza);
    }
}