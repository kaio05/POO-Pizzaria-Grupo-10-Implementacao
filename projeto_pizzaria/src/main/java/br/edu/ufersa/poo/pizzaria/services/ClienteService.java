package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.entities.Usuario;

import java.util.UUID;

public interface ClienteService extends Service<Cliente> {
    void changeName(UUID id, String newName);
    void changeAddress(UUID id, String newAddress);
    void changeNumber(UUID id, String newNumber);
    Cliente getByCpf(String cpf);
}
