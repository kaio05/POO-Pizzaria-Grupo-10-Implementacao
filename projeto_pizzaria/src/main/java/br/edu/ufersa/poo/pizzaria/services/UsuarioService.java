package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    Usuario getById(UUID id);
    Usuario getByEmail(String email);
    List<Usuario> getAll();
    void register(Usuario usuario);
    void changeEmail(UUID id, String newEmail);
    void changePassword(UUID id, String newPassword);
    void remove(UUID id);
    void login(String email, String password);
}
