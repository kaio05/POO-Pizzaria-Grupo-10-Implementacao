package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;

import java.util.UUID;

public interface ClienteService extends Service<Cliente> {
    Cliente getByCpf(Cliente cliente);
}
