package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.TipoPizza;
import br.edu.ufersa.poo.pizzaria.model.repositories.TipoPizzaRepository;
import br.edu.ufersa.poo.pizzaria.model.repositories.TipoPizzaRepositoryImpl;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class TipoPizzaServiceImpl extends ServiceImpl<TipoPizza> implements TipoPizzaService {

    private final TipoPizzaRepository repo;

    public TipoPizzaServiceImpl(EntityManager em) {
        super(new TipoPizzaRepositoryImpl(TipoPizza.class, em));
        this.repo = new TipoPizzaRepositoryImpl(TipoPizza.class, em);
    }


    @Override
    public void changeName(UUID id, String newName) {
        TipoPizza tipoPizza = repo.findById(new TipoPizza());
        if(tipoPizza == null) throw new IllegalArgumentException("TipoPizza não encontrado");
        tipoPizza.setNome(newName);
        repo.update(tipoPizza);
    }

    @Override
    public void changeValue(UUID id, double newValue) {
        TipoPizza tipoPizza = repo.findById(new TipoPizza());
        if(tipoPizza == null) throw new IllegalArgumentException("TipoPizza não encontrado");
        tipoPizza.setValor(newValue);
        repo.update(tipoPizza);
    }

    @Override
    public TipoPizza getByCode(String code) {
        return repo.findByCode(code);
    }

    @Override
    public void register(TipoPizza tipoPizza) {
        if(repo.findByCode(tipoPizza.getCodigo()) != null) {
            throw new IllegalArgumentException("Tipo já cadastrado");
        }
        repo.save(tipoPizza);
    }

    @Override
    public void update(TipoPizza tipoPizza) {

    }
}
