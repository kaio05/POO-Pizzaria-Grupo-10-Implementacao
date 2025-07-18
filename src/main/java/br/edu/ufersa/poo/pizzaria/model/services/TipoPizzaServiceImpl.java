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
    public void changeName(TipoPizza tp, String newName) {
        TipoPizza tipoPizza = repo.findById(tp);
        if(tipoPizza == null) {
            throw new IllegalArgumentException("TipoPizza não encontrado");
        }else{
            tipoPizza.setNome(newName);
            repo.update(tipoPizza);
        }
    }

    @Override
    public void changeValue(TipoPizza tp, double newValue) {
        TipoPizza tipoPizza = repo.findById(tp);
        if(tipoPizza == null) {
            throw new IllegalArgumentException("TipoPizza não encontrado");
        }else {
            tipoPizza.setValor(newValue);
            repo.update(tipoPizza);
        }
    }

    @Override
    public TipoPizza getByCode(TipoPizza tp) {
        return repo.findByCode(tp);
    }

    @Override
    public void register(TipoPizza tipoPizza) {
        if(repo.findByCode(tipoPizza) != null) {
            throw new IllegalArgumentException("Tipo já cadastrado");
        }
        repo.save(tipoPizza);
    }

    @Override
    public void update(TipoPizza tipoPizza) {

    }
}
