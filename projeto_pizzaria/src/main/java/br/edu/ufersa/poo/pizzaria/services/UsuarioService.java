package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioService extends Service<Usuario> {
    Usuario getByEmail(String email);
    void changeEmail(UUID id, String newEmail);
    void changePassword(UUID id, String newPassword);
    void login(String email, String password);
}
