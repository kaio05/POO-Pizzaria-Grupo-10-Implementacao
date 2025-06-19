package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.repositories.UsuarioRepository;
import br.edu.ufersa.poo.pizzaria.repositories.UsuarioRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo = new UsuarioRepositoryImpl();

    @Override
    public Usuario getById(UUID id) {
        return repo.findById(id);
    }
    @Override
    public Usuario getByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public List<Usuario> getAll() {
        return repo.findAll();
    }

    @Override
    public void register(Usuario usuario) {
        if(repo.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        // TODO: Fazer hash da senha
        repo.save(usuario);
    }

    @Override
    public void changeEmail(UUID id, String newEmail) {
        Usuario usuario = repo.findById(id);
        if(usuario == null) throw new IllegalArgumentException("Usuário não encontrado");
        usuario.setEmail(newEmail);
        repo.update(usuario);
    }

    @Override
    public void changePassword(UUID id, String newPassword) {
        Usuario usuario = repo.findById(id);
        if(usuario == null) throw new IllegalArgumentException("Usuário não encontrado");
        usuario.setSenha(newPassword);
        repo.update(usuario);
    }

    @Override
    public void remove(UUID id) {
        Usuario usuario = repo.findById(id);
        if(usuario != null) repo.delete(usuario);
    }

    @Override
    public void login(String email, String password) {
        // TODO: A implementar
        System.out.println("Usuário logado!");
    }
}
