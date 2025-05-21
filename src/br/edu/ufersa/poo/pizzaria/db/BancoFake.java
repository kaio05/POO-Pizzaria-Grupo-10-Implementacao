package br.edu.ufersa.poo.pizzaria.db;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import br.edu.ufersa.poo.pizzaria.entity.*;;

public class BancoFake {
    public static List<Cliente> clientes = new ArrayList<Cliente>();

    public static List<Usuario> usuarios = new ArrayList<Usuario>();

    public static List<Adicional> adicionais = new ArrayList<>();

    public static boolean exists(UUID id) {
        return true;
    }

    public static boolean exists(String nome) {
        return true;
    }

    public static boolean exists(String nome, String endereco, String cpf, String telefone) {
        return true;
    }

    public static boolean authenticate(String nome, String senha) {
        return true;
    }

    public static void save(Cliente cliente) {
        System.out.println("Salvo com sucesso!");
    }

    public static void save(Usuario usuario) {
        System.out.println("Salvo com sucesso!");
    }

    public static void save(Adicional adicional){
        adicionais.add(adicional);
        System.out.println("Salvo com sucesso!");
    }

    public static void update(Cliente cliente) {
        System.out.println("Salvo com sucesso!");
    }

    public static void update(Usuario usuario) {
        System.out.println("Salvo com sucesso!");
    }

    public static void update(Adicional adicional){
        System.out.println("Salvo com sucesso!");
    }

    public static void remove(UUID id) {
        System.out.println("Removido com sucesso!");
    }

    public static void remove(Adicional adicional) {
        System.out.println("Removido com sucesso!");
    }
}
