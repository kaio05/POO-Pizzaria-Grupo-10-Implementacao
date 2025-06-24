package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Pizza;
import br.edu.ufersa.poo.pizzaria.repositories.PizzaRepository;
import br.edu.ufersa.poo.pizzaria.repositories.PizzaRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;

import java.util.List;
import java.util.UUID;

public class PizzaServiceImpl extends ServiceImpl<Pizza> implements PizzaService {

    public PizzaServiceImpl() {
        super(new PizzaRepositoryImpl(Pizza.class, JPAUtil.getEntityManagerFactory()));
    }

    private final PizzaRepository repo = new PizzaRepositoryImpl(Pizza.class, JPAUtil.getEntityManagerFactory());

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
        System.out.println("Pizza é registrada pelo pedido");
    }
}