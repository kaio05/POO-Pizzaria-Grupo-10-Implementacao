package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.repositories.ClienteRepository;
import br.edu.ufersa.poo.pizzaria.repositories.ClienteRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.repositories.Repository;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.UUID;

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
