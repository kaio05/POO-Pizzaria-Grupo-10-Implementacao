package br.edu.ufersa.poo.pizzaria.model.services;

import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;

public interface UsuarioService extends Service<Usuario> {
    Usuario getByEmail(Usuario usuario);
    void login(Usuario usuario);
}
