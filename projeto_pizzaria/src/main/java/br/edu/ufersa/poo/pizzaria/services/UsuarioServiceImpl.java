package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.repositories.Repository;
import br.edu.ufersa.poo.pizzaria.repositories.UsuarioRepository;
import br.edu.ufersa.poo.pizzaria.repositories.UsuarioRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import br.edu.ufersa.poo.pizzaria.utils.Session;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.UUID;

public class UsuarioServiceImpl extends ServiceImpl<Usuario> implements UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioServiceImpl(EntityManager em) {
        super(new UsuarioRepositoryImpl(Usuario.class, em));
        repo = new UsuarioRepositoryImpl(Usuario.class,em);
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
        if(repo.findById(usuario) == null) throw new IllegalArgumentException("Usuário não encontrado");
        repo.update(usuario);
    }

    @Override
    public Usuario getByEmail(Usuario usuario) {
        return repo.findByEmail(usuario);
    }

    @Override
    public void login(Usuario usuario) {
        Usuario usuarioExists = repo.findByEmail(usuario);
        if(usuarioExists == null) throw new IllegalArgumentException("Usuário não encontrado");
        if(!usuarioExists.getSenha().equals(usuario.getSenha())) throw new IllegalArgumentException("Usuário ou senha incorretos");
        Session.setUsuario(usuario);
    }
}
