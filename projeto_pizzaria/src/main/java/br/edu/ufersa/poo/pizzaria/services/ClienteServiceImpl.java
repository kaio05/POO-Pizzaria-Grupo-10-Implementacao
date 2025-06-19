package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.repositories.ClienteRepository;
import br.edu.ufersa.poo.pizzaria.repositories.ClienteRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.repositories.Repository;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.UUID;

public class ClienteServiceImpl extends ServiceImpl<Cliente> implements ClienteService {

    public ClienteServiceImpl() {
        super(new ClienteRepositoryImpl(Cliente.class, JPAUtil.getEntityManagerFactory()));
    }

    private final ClienteRepository repo = new ClienteRepositoryImpl(Cliente.class, JPAUtil.getEntityManagerFactory());

    @Override
    public Cliente getByCpf(String cpf) {
        return repo.findByCpf(cpf);
    }

    @Override
    public void register(Cliente cliente) {
        if(repo.findByCpf(cliente.getCpf()) != null) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        repo.save(cliente);
    }


    @Override
    public void changeName(UUID id, String newName) {
        Cliente cliente = repo.findById(id);
        if(cliente == null) throw new IllegalArgumentException("Cliente não encontrado");
        cliente.setNome(newName);
        repo.update(cliente);
    }

    @Override
    public void changeAddress(UUID id, String newAddress) {
        Cliente cliente = repo.findById(id);
        if(cliente == null) throw new IllegalArgumentException("Cliente não encontrado");
        cliente.setEndereco(newAddress);
        repo.update(cliente);
    }

    @Override
    public void changeNumber(UUID id, String newNumber) {
        Cliente cliente = repo.findById(id);
        if(cliente == null) throw new IllegalArgumentException("Cliente não encontrado");
        cliente.setTelefone(newNumber);
        repo.update(cliente);
    }
}
