package br.edu.ufersa.poo.pizzaria.repositories;

import br.edu.ufersa.poo.pizzaria.entities.Cliente;
import br.edu.ufersa.poo.pizzaria.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class PedidosRepositoryImpl extends RepositoryImpl<Pedido> implements PedidosRepository {

    public PedidosRepositoryImpl(EntityManager em) {
        super(Pedido.class, em);
    }
