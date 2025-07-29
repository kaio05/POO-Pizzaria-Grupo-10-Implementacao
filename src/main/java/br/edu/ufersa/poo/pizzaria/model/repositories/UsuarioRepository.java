package br.edu.ufersa.poo.pizzaria.model.repositories;

import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;

public interface UsuarioRepository extends Repository<Usuario> {
    Usuario findByEmail(Usuario usuario);
    boolean findAdmin(Usuario usuario);
}
