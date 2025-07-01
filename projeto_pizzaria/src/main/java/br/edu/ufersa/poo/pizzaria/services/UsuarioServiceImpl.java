package br.edu.ufersa.poo.pizzaria.services;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.repositories.Repository;
import br.edu.ufersa.poo.pizzaria.repositories.UsuarioRepository;
import br.edu.ufersa.poo.pizzaria.repositories.UsuarioRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;

import java.util.List;
import java.util.UUID;

public class UsuarioServiceImpl extends ServiceImpl<Usuario> implements UsuarioService {

    public UsuarioServiceImpl() {
        super(new UsuarioRepositoryImpl(Usuario.class, JPAUtil.getEntityManagerFactory()));
    }
    private final UsuarioRepository repo = new UsuarioRepositoryImpl(Usuario.class, JPAUtil.getEntityManagerFactory());

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
        // TODO: A implementar
        System.out.println("Usuário logado!");
    }
}
