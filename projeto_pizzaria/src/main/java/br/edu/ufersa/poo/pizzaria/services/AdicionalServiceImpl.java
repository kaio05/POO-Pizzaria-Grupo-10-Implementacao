package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.repositories.AdicionalRepository;
import br.edu.ufersa.poo.pizzaria.repositories.AdicionalRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;

import java.util.UUID;

public class AdicionalServiceImpl extends ServiceImpl<Adicional> implements AdicionalService {

    public AdicionalServiceImpl() {
        super(new AdicionalRepositoryImpl(Adicional.class, JPAUtil.getEntityManagerFactory()));
    }

    private final AdicionalRepository repo = new AdicionalRepositoryImpl(Adicional.class, JPAUtil.getEntityManagerFactory());

    @Override
    public void changeCode(UUID id, String newCode) {
        Adicional adicional = repo.findById(id);
        if(adicional == null) throw new IllegalArgumentException("Adicional não encontrado");
        adicional.setCodigo(newCode);
        repo.update(adicional);
    }

    @Override
    public void changeName(UUID id, String newName) {
        Adicional adicional = repo.findById(id);
        if(adicional == null) throw new IllegalArgumentException("Adicional não encontrado");
        adicional.setNome(newName);
        repo.update(adicional);
    }

    @Override
    public void changeValue(UUID id, double newValue) {
        Adicional adicional = repo.findById(id);
        if(adicional == null) throw new IllegalArgumentException("Adicional não encontrado");
        adicional.setValor(newValue);
        repo.update(adicional);
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
}
