package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.entities.TipoPizza;
import br.edu.ufersa.poo.pizzaria.repositories.TipoPizzaRepository;
import br.edu.ufersa.poo.pizzaria.repositories.TipoPizzaRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;

import java.util.UUID;

public class TipoPizzaServiceImpl extends ServiceImpl<TipoPizza> implements TipoPizzaService {

    public TipoPizzaServiceImpl() {
        super(new TipoPizzaRepositoryImpl(TipoPizza.class, JPAUtil.getEntityManagerFactory()));
    }

    private final TipoPizzaRepository repo = new TipoPizzaRepositoryImpl(TipoPizza.class, JPAUtil.getEntityManagerFactory());

    @Override
    public void changeName(UUID id, String newName) {
        TipoPizza tipoPizza = repo.findById(id);
        if(tipoPizza == null) throw new IllegalArgumentException("TipoPizza não encontrado");
        tipoPizza.setNome(newName);
        repo.update(tipoPizza);
    }

    @Override
    public void changeValue(UUID id, double newValue) {
        TipoPizza tipoPizza = repo.findById(id);
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
}
