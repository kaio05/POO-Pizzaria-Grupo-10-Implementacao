package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.Cliente;

public interface ClienteService extends Service<Cliente> {
    Cliente getByCpf(Cliente cliente);
}
