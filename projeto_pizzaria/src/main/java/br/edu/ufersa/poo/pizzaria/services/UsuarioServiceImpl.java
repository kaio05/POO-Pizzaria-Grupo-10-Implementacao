package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.repositories.UsuarioRepository;
import br.edu.ufersa.poo.pizzaria.repositories.UsuarioRepositoryImpl;

import java.util.List;
import java.util.UUID;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo = new UsuarioRepositoryImpl();

    @Override
    public Usuario getById(Usuario usuario) {
        return repo.findById(usuario);
    }
    @Override
    public Usuario getByEmail(Usuario usuario) {
        return repo.findByEmail(usuario);
    }

    @Override
    public List<Usuario> getAll() {
        return repo.findAll();
    }

    @Override
    public void register(Usuario usuario) {
        if(repo.findByEmail(usuario) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        // TODO: Fazer hash da senha
        repo.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        Usuario usuarioExists = repo.findById(usuario);
        if(usuarioExists == null) throw new IllegalArgumentException("Usuário não encontrado");
        repo.update(usuario);
    }

    @Override
    public void remove(Usuario usuario) {
        Usuario usuarioExists = repo.findById(usuario);
        if(usuarioExists != null) repo.delete(usuario);
    }

    @Override
    public void login(String email, String password) {
        // TODO: A implementar
        System.out.println("Usuário logado!");
    }
}
