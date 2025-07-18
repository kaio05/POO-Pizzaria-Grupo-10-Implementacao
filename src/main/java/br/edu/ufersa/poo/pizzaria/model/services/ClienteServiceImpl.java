package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.model.repositories.ClienteRepository;
import br.edu.ufersa.poo.pizzaria.model.repositories.ClienteRepositoryImpl;
import jakarta.persistence.EntityManager;

public class ClienteServiceImpl extends ServiceImpl<Cliente> implements ClienteService {

    private final ClienteRepository repo;

    public ClienteServiceImpl(EntityManager em) {
        super(new ClienteRepositoryImpl(Cliente.class, em));
        this.repo = new ClienteRepositoryImpl(Cliente.class, em);
    }

    @Override
    public Cliente getByCpf(Cliente cliente) {
        return repo.findByCpf(cliente);
    }

    @Override
    public void register(Cliente cliente) {
        if(repo.findByCpf(cliente) != null) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        repo.save(cliente);
    }

    @Override
    public void update(Cliente cliente) {
        if(repo.findById(cliente) == null) {
            throw new IllegalArgumentException("Cliente não cadastrado");
        }
        repo.update(cliente);
    }
}
