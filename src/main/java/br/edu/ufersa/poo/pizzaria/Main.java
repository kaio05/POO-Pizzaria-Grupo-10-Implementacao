package br.edu.ufersa.poo.pizzaria;

import br.edu.ufersa.poo.pizzaria.model.entities.*;
import br.edu.ufersa.poo.pizzaria.model.services.*;
import br.edu.ufersa.poo.pizzaria.utils.EMSingleton;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new ClienteServiceImpl(EMSingleton.getInstance()).getByCpf(new Cliente("", "", "22222222222", ""));
        List<Adicional> adicionais = new ArrayList<Adicional>();
        new AdicionalServiceImpl(EMSingleton.getInstance()).register(new Adicional("0005", "Carne de Sol", 3.5));
        adicionais.add(new AdicionalServiceImpl(EMSingleton.getInstance()).getByCode(new Adicional("0005", null, 0)));
        TipoPizza tipo = new TipoPizza("0005", "Mussarela", 19.0);
        TipoPizzaService tipoPizzaService = new TipoPizzaServiceImpl(EMSingleton.getInstance());
        tipoPizzaService.register(tipo);
        tipo = tipoPizzaService.getByCode(tipo);
        Pizza pizza = new Pizza(tipo, cliente);
        PizzaService pizzaService = new PizzaServiceImpl(EMSingleton.getInstance());
        pizzaService.register(pizza);
        pizza = pizzaService.getById(pizza);
        PedidoService pedidoService = new PedidoServiceImpl(EMSingleton.getInstance());
        Pedido pedido = new Pedido(cliente, adicionais, pizza, Estado.NOVO, Tamanho.G, new Date(125, 6, 27));
        pedidoService.register(pedido);
    }
}
