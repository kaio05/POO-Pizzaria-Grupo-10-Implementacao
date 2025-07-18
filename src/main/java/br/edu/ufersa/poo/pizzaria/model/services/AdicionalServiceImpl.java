package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.model.repositories.AdicionalRepository;
import br.edu.ufersa.poo.pizzaria.model.repositories.AdicionalRepositoryImpl;
import jakarta.persistence.EntityManager;

public class AdicionalServiceImpl extends ServiceImpl<Adicional> implements AdicionalService {

    private final AdicionalRepository repo;

    public AdicionalServiceImpl(EntityManager em) {
        super(new AdicionalRepositoryImpl(Adicional.class, em));
        this.repo = new AdicionalRepositoryImpl(Adicional.class, em);
    }


    @Override
    public Adicional getByCode(Adicional adic) {
        return repo.findByCode(adic);
    }

    @Override
    public void register(Adicional adicional) {
        if(repo.findByCode(adicional) != null) {
            throw new IllegalArgumentException("Código já cadastrado");
        }
        repo.save(adicional);
    }

    @Override
    public void update(Adicional adicional) {
        // a implementar
    }
}
