package br.edu.ufersa.poo.pizzaria;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.entities.TipoPizza;
import br.edu.ufersa.poo.pizzaria.services.*;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;

public class Main {
    public static void main(String[] args) {
// TODO: Chame o serviço e adicione pelo menos dois usuários no banco de dados. Tente utilizar os outros métodos também

//        UsuarioService usuarioService = new UsuarioServiceImpl();
//        Usuario usuario = new Usuario("Michelangelo", "mich@example.com", "123456");
//        usuarioService.register(usuario); // Criando um usuário
//
//        usuarioService.getAll().forEach(System.out::println);
//
//        Usuario usuarioUpdate = usuarioService.getByEmail("mich@example.com"); // atualizando
//        usuarioUpdate.setEmail("michelangelo@example.com");
//
//        usuarioService.getAll().forEach(System.out::println);
//
//        usuarioService.remove(usuarioUpdate.getId()); // removendo
//
//        usuarioService.getAll().forEach(System.out::println);
//

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
//        System.out.println(tipoPizzaService.getAll());

        JPAUtil.shutdown();
    }
}
