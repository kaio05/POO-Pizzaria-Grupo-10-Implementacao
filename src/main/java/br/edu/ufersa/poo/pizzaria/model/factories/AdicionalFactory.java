package br.edu.ufersa.poo.pizzaria.model.factories;

import br.edu.ufersa.poo.pizzaria.model.entities.Adicional;

/**
 * Abstract factory interface for creating Adicional objects.
 * This follows the Factory Method pattern.
 */
public interface AdicionalFactory {
    /**
     * Creates an Adicional object.
     * @param nome The name of the additional
     * @param valor The price of the additional
     * @return A new Adicional object
     */
    Adicional createAdicional(String nome, double valor);
}