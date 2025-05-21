package br.edu.ufersa.poo.pizzaria.db;
import java.util.List;
import java.util.ArrayList;
import br.edu.ufersa.poo.pizzaria.entity.*;;

public class BancoFake {
    public static List<Cliente> clientes = new ArrayList<Cliente>();

    public static List<Usuario> usuarios = new ArrayList<Usuario>();

    public static boolean exists(Usuario usuario) {
        if(usuario.getId() != null) {
            for(Usuario u: usuarios) {
                if(u.getId().equals(usuario.getId())) {
                    return true;
                }
            }
            return false;
        } else {
            for(Usuario u: usuarios) {
                if(u.getEmail().equals(usuario.getEmail())) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public static boolean authenticate(Usuario usuario) {
        for(Usuario u: usuarios) {
            if(u.getEmail().equals(usuario.getEmail()) && u.getSenha().equals(usuario.getSenha())) {
                return true;
            }
        }
        return false;
    } 

    public static void save(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Salvo com sucesso!");
    }

    public static void update(Usuario usuario) {
        for(Usuario u: usuarios) {
            if(u.getId().equals(usuario.getId())) {
                if (usuario.getNome() != null) u.setNome(usuario.getNome());
                if (usuario.getEmail() != null) u.setEmail(usuario.getEmail());
                if (usuario.getSenha() != null) u.setSenha(usuario.getSenha());
                System.out.println("Atualizado com sucesso!");
                return;
            }
        }
    }

    public static void remove(Usuario usuario) {
        for(Usuario u: usuarios) {
            if(u.getId().equals(usuario.getId())) {
                usuarios.remove(u);
                System.out.println("Removido com sucesso!");
                return;
            }
        }
    }

    // cliente
    public static boolean exists(Cliente cliente) {
        if(cliente.getId() != null) {
            for(Cliente c: clientes) {
                if(c.getId().equals(cliente.getId())) {
                    return true;
                }
            }
            return false;
        } else {
            for(Cliente c: clientes) {
                if(c.getCpf().equals(cliente.getCpf())) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void save(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Salvo com sucesso!");
    }

    public static void update(Cliente cliente) {
        for(Cliente c: clientes) {
            if(c.getId().equals(cliente.getId())) {
                if (cliente.getCpf() != null) c.setCpf(cliente.getCpf());
                if (cliente.getEndereco() != null) c.setEndereco(cliente.getEndereco());
                if (cliente.getNome() != null) c.setNome(cliente.getNome());
                if (cliente.getTelefone() != null) c.setTelefone(cliente.getTelefone());
                System.out.println("Atualizado com sucesso!");
                return;
            }
        }
    }

    public static void remove(Cliente cliente) {
        for(Cliente c: clientes) {
            if(c.getId().equals(cliente.getId())) {
                clientes.remove(c);
                System.out.println("Removido com sucesso!");
                return;
            }
        }
    }
}
