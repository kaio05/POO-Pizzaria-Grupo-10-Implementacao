package br.edu.ufersa.poo.meuprojeto.exceptions;

public class NotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super ("Entidade não encontrada");
    }
}
