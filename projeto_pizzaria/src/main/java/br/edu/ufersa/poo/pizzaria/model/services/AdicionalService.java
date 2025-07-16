package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.Adicional;

public interface AdicionalService extends Service<Adicional> {
    Adicional getByCode(Adicional adic);
}