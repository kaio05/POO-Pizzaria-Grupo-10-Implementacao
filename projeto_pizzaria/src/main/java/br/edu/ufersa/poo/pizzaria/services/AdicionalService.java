package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;

import java.util.UUID;

public interface AdicionalService extends Service<Adicional> {
    Adicional getByCode(String codigo);
}