package br.edu.ufersa.poo.pizzaria;

import br.edu.ufersa.poo.pizzaria.entities.Usuario;
import br.edu.ufersa.poo.pizzaria.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.repositories.ClienteRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.services.ClienteServiceImpl;
import br.edu.ufersa.poo.pizzaria.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.entities.TipoPizza;
import br.edu.ufersa.poo.pizzaria.services.*;
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

//        Adicional

//        AdicionalService adicionalService = new AdicionalServiceImpl();

//        Adicional adicional = new Adicional("0001", "Bacon", 5.90);
//        adicionalService.register(adicional);
//        adicionalService.getAll().forEach(System.out::println);

//        Adicional adicionalMod = adicionalService.getByCode("0001");
//        adicionalService.changeValue(adicionalMod.getId(), 6.20);
//        adicionalService.getAll().forEach(System.out::println);
//
//        adicionalService.remove(adicionalMod.getId());
//        adicionalService.getAll().forEach(System.out::println);

//        Tipo Pizza

//        TipoPizzaService tipoPizzaService = new TipoPizzaServiceImpl();
//
//        TipoPizza tipo = new TipoPizza("0001", "Calabresa", 17.0);
//        tipoPizzaService.register(tipo);
//        System.out.println(tipoPizzaService.getAll());
//
//        TipoPizza tipoMod = tipoPizzaService.getByCode("0001");
//
//        tipoPizzaService.changeName(tipoMod.getId(), "4 Queijos");
//        System.out.println(tipoPizzaService.getAll());

//        tipoPizzaService.remove(tipoMod.getId());
//        System.out.println(tipoPizzaService.getAll());=

        JPAUtil.shutdown();
    }
}
