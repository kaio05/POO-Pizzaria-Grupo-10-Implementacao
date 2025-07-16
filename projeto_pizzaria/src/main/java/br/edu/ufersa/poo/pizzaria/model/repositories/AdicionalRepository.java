package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.Adicional;

public interface AdicionalRepository extends Repository<Adicional>{
    Adicional findByCode(String code);
}