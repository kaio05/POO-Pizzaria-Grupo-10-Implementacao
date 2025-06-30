package br.edu.ufersa.poo.pizzaria;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.repositories.ClienteRepositoryImpl;
import br.edu.ufersa.poo.pizzaria.services.ClienteServiceImpl;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
// TODO: Chame o serviço e adicione pelo menos dois usuários no banco de dados. Tente utilizar os outros métodos também
        var cService = new ClienteServiceImpl();
        var cliente = new Cliente("Francisco", "av. presidente erika hilton", "45342134769", "5544332211");
        cService.register(cliente);
        cService.getAll().forEach(System.out::println);

        var clienteMod = cService.getByCpf(cliente);
        clienteMod.setTelefone("1122334455");
        cService.update(clienteMod);
        cService.getAll().forEach(System.out::println);

        cService.remove(clienteMod);
        cService.getAll().forEach(System.out::println);

        JPAUtil.shutdown();
    }
}
