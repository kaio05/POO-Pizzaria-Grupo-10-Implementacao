package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;

import java.util.UUID;

public interface AdicionalService extends Service<Adicional> {
    void changeCode(UUID id, String newCode);
    void changeName(UUID id, String newName);
    void changeValue(UUID id, double newValue);
    Adicional getByCode(String codigo);
}