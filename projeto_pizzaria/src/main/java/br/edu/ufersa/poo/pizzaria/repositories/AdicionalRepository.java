package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;

import java.util.List;
import java.util.UUID;

public interface AdicionalRepository extends Repository<Adicional>{
    Adicional findByCod(String cod);
}