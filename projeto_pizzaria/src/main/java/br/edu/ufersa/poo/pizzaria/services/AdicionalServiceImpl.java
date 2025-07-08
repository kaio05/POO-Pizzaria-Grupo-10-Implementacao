package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.repositories.AdicionalRepository;
import br.edu.ufersa.poo.pizzaria.repositories.AdicionalRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class AdicionalServiceImpl extends ServiceImpl<Adicional> implements AdicionalService {

    private final AdicionalRepository repo;

    public AdicionalServiceImpl(EntityManager em) {
        super(new AdicionalRepositoryImpl(Adicional.class, em));
        this.repo = new AdicionalRepositoryImpl(Adicional.class, em);
    }


    @Override
    public Adicional getByCode(String code) {
        return repo.findByCode(code);
    }

    @Override
    public void register(Adicional adicional) {
        if(repo.findByCode(adicional.getCodigo()) != null) {
            throw new IllegalArgumentException("Código já cadastrado");
        }
        repo.save(adicional);
    }

    @Override
    public void update(Adicional adicional) {
        // a implementar
    }
}
