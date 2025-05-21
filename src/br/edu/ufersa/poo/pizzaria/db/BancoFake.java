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

    public static boolean exists(Cliente cliente) {
        return true;
    }

    public static boolean authenticate(Usuario usuario) {
        return true;
    }

    public static void save(Cliente cliente) {
        System.out.println("Salvo com sucesso!");
    }

    public static void save(Usuario usuario) {
        System.out.println("Salvo com sucesso!");
    }

    public static void update(Cliente cliente) {
        System.out.println("Salvo com sucesso!");
    }

    public static void update(Usuario usuario) {
        System.out.println("Salvo com sucesso!");
    }

    public static void remove(UUID id) {
        System.out.println("Removido com sucesso!");
    }
}
