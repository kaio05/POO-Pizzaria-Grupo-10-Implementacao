package br.edu.ufersa.poo.pizzaria.builder;

import br.edu.ufersa.poo.pizzaria.builder.Builder;

import br.edu.ufersa.poo.pizzaria.model.entities.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PedidoBuilderImpl implements Builder {
    private UUID id;
    private Cliente cliente;
    private List<Adicional> adicionais = new ArrayList<>();
    private Pizza pizza;
    private Estado estado;
    private Tamanho tamanho;
    private Date data;

    @Override
    public Builder withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public Builder withCliente(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("Cliente não pode ser nulo");
        this.cliente = cliente;
        return this;
    }

    @Override
    public Builder withAdicionais(List<Adicional> adicionais) {
        if (adicionais != null) this.adicionais = adicionais;
        return this;
    }

    @Override
    public Builder addAdicional(Adicional adicional) {
        if (adicional != null) this.adicionais.add(adicional);
        return this;
    }

    @Override
    public Builder withPizza(Pizza pizza) {
        if (pizza == null) throw new IllegalArgumentException("Pizza não pode ser nula");
        this.pizza = pizza;
        return this;
    }

    @Override
    public Builder withEstado(Estado estado) {
        if (estado == null) throw new IllegalArgumentException("Estado não pode ser nulo");
        this.estado = estado;
        return this;
    }

    @Override
    public Builder withTamanho(Tamanho tamanho) {
        if (tamanho == null) throw new IllegalArgumentException("Tamanho não pode ser nulo");
        this.tamanho = tamanho;
        return this;
    }

    @Override
    public Builder withData(java.util.Date data) {
        return null;
    }

    @Override
    public Builder withData(Date data) {
        if (data == null) throw new IllegalArgumentException("Data não pode ser nula");
        this.data = data;
        return this;
    }

    @Override
    public Pedido build() {
        Pedido pedido = new Pedido();
        pedido.setCliente(this.cliente);
        pedido.setAdicionais(this.adicionais);
        pedido.setPizza(this.pizza);
        pedido.setEstado(this.estado);
        pedido.setTamanho(this.tamanho);
        pedido.setData(this.data);

        if (this.id != null) pedido.setId(this.id);

        return pedido;
    }
}