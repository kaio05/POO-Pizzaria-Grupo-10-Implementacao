package br.edu.ufersa.poo.pizzaria.model.factories;

import br.edu.ufersa.poo.pizzaria.model.entities.Adicional;

/**
 * Default implementation of AdicionalFactory.
 * Creates standard additional items with a code based on the name and a random number.
 */
public class DefaultAdicionalFactory implements AdicionalFactory {
    
    @Override
    public Adicional createAdicional(String nome, double valor) {
        // Generate a unique code based on the name
        String codigo = nome.substring(0, Math.min(3, nome.length())).toUpperCase() + 
                       String.format("%03d", (int)(Math.random() * 1000));
        
        return new Adicional(codigo, nome, valor);
    }
}