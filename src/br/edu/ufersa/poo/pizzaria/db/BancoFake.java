package br.edu.ufersa.poo.pizzaria.db;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import br.edu.ufersa.poo.pizzaria.entity.*;;

public class BancoFake {
    public static List<Cliente> clientes = new ArrayList<Cliente>();

    public static List<Usuario> usuarios = new ArrayList<Usuario>();

    public static boolean exists(UUID id) {
        return true;
    }

    public static boolean exists(Usuario usuario) {
        return true;
    }
    
    public static boolean authenticate(Usuario usuario) {
        return true;
    } 

    public static void save(Usuario usuario) {
        System.out.println("Salvo com sucesso!");
    }

    public static void update(Usuario usuario) {
        System.out.println("Salvo com sucesso!");
    }

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
                return;
            }
        }
        System.out.println("Atualizado com sucesso!");
    }

    public static void remove(Cliente cliente) {
        for(Cliente c: clientes) {
            if(c.getId().equals(cliente.getId())) {
                clientes.remove(c);
                return;
            }
        }
        System.out.println("Removido com sucesso!");
    }
}
