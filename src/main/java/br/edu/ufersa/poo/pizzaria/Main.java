package br.edu.ufersa.poo.pizzaria;

import br.edu.ufersa.poo.pizzaria.model.entities.*;
import br.edu.ufersa.poo.pizzaria.model.services.*;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        // TODO: Chame o serviço e adicione pelo menos dois usuários no banco de dados. Tente utilizar os outros métodos também

        EntityManager em = JPAUtil.getEntityManagerFactory();

//        var adService = new AdicionalServiceImpl(em);
//        var codAdicional1 = new Adicional("0003","Palmito",3.50);
//        adService.register(codAdicional1);
//        var codAdicional2 = new Adicional("0004","Bacon",5);
//        adService.register(codAdicional2);
//        var adicional1 = adService.getByCode("0003");
//        var adicional2 = adService.getByCode("0004");
//        List<Adicional> adicionais = new ArrayList<Adicional>();
//        adicionais.add(adicional1);

//        var tipoCod = new TipoPizza("0005", "Portuguesa", 15);
//        var tpService = new TipoPizzaServiceImpl(em);
//        tpService.register(tipoCod);
//        var tipo = tpService.getByCode("0005");

        var cService = new ClienteServiceImpl(em);
        var clienteCpf = new Cliente("João", "rua dos pássaros", "591819021", "88945672345");
//        cService.register(clienteCpf);
        var cliente = cService.getByCpf(clienteCpf);

//        var pizzaService = new PizzaServiceImpl(em);
//        var pizzaReg = new Pizza(tipo, cliente);
//        pizzaService.register(pizzaReg);
//        var pizza = pizzaService.getAll().get(0);
//
//        var estado = Estado.NOVO;
//        var tamanho = Tamanho.M;
//        var data = new Date(2025, 7, 8);


        var pService = new PedidoServiceImpl(em);
//        var pedidoReg = new Pedido(cliente, adicionais, pizza, estado, tamanho, data);
//        pService.register(pedidoReg);
        var pedido = pService.getByCliente(cliente).get(0);
        pService.getAll().forEach(System.out::println);
        pService.remove(pedido);
        pService.getAll().forEach(System.out::println);
        JPAUtil.shutdown();
    }
}
