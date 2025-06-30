package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    Usuario getById(Usuario usuario);
    Usuario getByEmail(Usuario usuario);
    List<Usuario> getAll();
    void register(Usuario usuario);
    void update(Usuario usuario);
    void remove(Usuario usuario);
    void login(String email, String password);
}
