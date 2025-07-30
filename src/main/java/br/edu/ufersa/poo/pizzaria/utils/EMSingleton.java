package br.edu.ufersa.poo.pizzaria.utils;

import jakarta.persistence.EntityManager;

public abstract class EMSingleton implements EntityManager {
    private static volatile EntityManager em;

    public static EntityManager getInstance() {
        synchronized (EMSingleton.class) {
            if (em == null) {
                em = JPAUtil.getEntityManagerFactory();
            }
            return em;
        }
    }
}
