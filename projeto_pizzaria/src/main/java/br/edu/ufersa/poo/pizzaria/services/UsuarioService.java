package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioService extends Service<Usuario> {
    Usuario getByEmail(Usuario usuario);
    void login(Usuario usuario);
}
