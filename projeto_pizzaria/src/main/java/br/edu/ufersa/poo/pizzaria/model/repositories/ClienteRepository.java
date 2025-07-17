package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;

public interface ClienteRepository extends Repository<Cliente> {
    Cliente findByCpf(Cliente cliente);
}
