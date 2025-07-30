package br.edu.ufersa.poo.pizzaria.builder;


import br.edu.ufersa.poo.pizzaria.model.entities.*;
import javafx.scene.control.Label;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface Builder {
        Builder withId(UUID id);
        Builder withCliente(Cliente cliente);
        Builder withAdicionais(List<Adicional> adicionais);
        Builder addAdicional(Adicional adicional);
        Builder withPizza(Pizza pizza);
        Builder withEstado(Estado estado);
        Builder withTamanho(Tamanho tamanho);
        Builder withData(Date data);

        Builder withData(java.sql.Date data);

        Pedido build();

}
