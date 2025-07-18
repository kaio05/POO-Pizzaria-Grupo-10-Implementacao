package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.Pizza;
import br.edu.ufersa.poo.pizzaria.model.repositories.PizzaRepository;
import br.edu.ufersa.poo.pizzaria.model.repositories.PizzaRepositoryImpl;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

public class PizzaServiceImpl extends ServiceImpl<Pizza> implements PizzaService {

    private final PizzaRepository repo;

    public PizzaServiceImpl(EntityManager em) {
        super(new PizzaRepositoryImpl(Pizza.class, em));
        this.repo = new PizzaRepositoryImpl(Pizza.class, em);
    }


    @Override
    public List<Pizza> getByClientId(UUID clienteId) {
        return repo.findByClienteId(clienteId);
    }

    @Override
    public List<Pizza> getByType(UUID tipoPizzaId) {
        return repo.findByType(tipoPizzaId);
    }

    @Override
    public void register(Pizza pizza) {
        repo.save(pizza);
    }

    @Override
    public void update(Pizza pizza) {

    }
}