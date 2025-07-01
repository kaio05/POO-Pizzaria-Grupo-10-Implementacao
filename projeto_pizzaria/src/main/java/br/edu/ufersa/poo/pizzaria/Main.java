package br.edu.ufersa.poo.pizzaria;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;

public class Main {
    public static void main(String[] args) {
// TODO: Chame o serviço e adicione pelo menos dois usuários no banco de dados. Tente utilizar os outros métodos também
//
//        var cService = new ClienteServiceImpl();
//        var cliente = new Cliente("Francisco", "av. presidente erika hilton", "45342134769", "5544332211");
//        cService.register(cliente);
//        cService.getAll().forEach(System.out::println);
//
//        var clienteMod = cService.getByCpf(cliente);
//        clienteMod.setTelefone("1122334455");
//        cService.update(clienteMod);
//        cService.getAll().forEach(System.out::println);
//
//        cService.remove(clienteMod);
//        cService.getAll().forEach(System.out::println);
//
        var uService = new UsuarioServiceImpl();
        var usuario = new Usuario("Williams", "will@example.com", "12345678");
        uService.register(usuario);
        System.out.println(usuario);
        uService.getAll().forEach(System.out::println);

        var usuarioMod = uService.getByEmail(usuario);
        usuarioMod.setNome("Will");
        System.out.println(usuarioMod);
        uService.update(usuarioMod);
        uService.getAll().forEach(System.out::println);

        uService.remove(usuarioMod);
        uService.getAll().forEach(System.out::println);

        JPAUtil.shutdown();
    }
}
