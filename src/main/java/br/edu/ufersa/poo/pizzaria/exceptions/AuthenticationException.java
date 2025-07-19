package br.edu.ufersa.poo.pizzaria.exceptions;

public class AuthenticationException extends Exception{
  private static final long serialVersionUID = 1L;

  public AuthenticationException() {
    super ("Login ou senha não encontrados");
  }

}
