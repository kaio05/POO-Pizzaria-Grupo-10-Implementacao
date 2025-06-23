package br.edu.ufersa.poo.pizzaria;

import br.edu.ufersa.poo.pizzaria.entities.Adicional;
import br.edu.ufersa.poo.pizzaria.entities.TipoPizza;
import br.edu.ufersa.poo.pizzaria.services.*;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;

public class Main {
    public static void main(String[] args) {
// TODO: Chame o serviço e adicione pelo menos dois usuários no banco de dados. Tente utilizar os outros métodos também

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

        TipoPizzaService tipoPizzaService = new TipoPizzaServiceImpl();

        TipoPizza tipo = new TipoPizza("0001", "Calabresa", 17.0);
        tipoPizzaService.register(tipo);
        System.out.println(tipoPizzaService.getAll());

        TipoPizza tipoMod = tipoPizzaService.getByCode("0001");

        tipoPizzaService.changeName(tipoMod.getId(), "4 Queijos");
        System.out.println(tipoPizzaService.getAll());
//
        tipoPizzaService.remove(tipoMod.getId());
        System.out.println(tipoPizzaService.getAll());

        JPAUtil.shutdown();
    }
}
