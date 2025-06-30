package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends Repository<Cliente> {
    Cliente findByCpf(Cliente cliente);
}
