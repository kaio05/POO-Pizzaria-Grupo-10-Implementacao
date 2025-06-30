package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioRepository {
    Usuario findById(Usuario usuario);
    List<Usuario> findAll();
    void save(Usuario usuario);
    Usuario update(Usuario usuario);
    void delete(Usuario usuario);
    Usuario findByEmail(Usuario usuario);
}
