package br.edu.ufersa.poo.pizzaria.utils;

import br.edu.ufersa.poo.pizzaria.model.entities.Usuario;

public class Session {
    private static Usuario usuario;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        if(usuario != null) {
            Session.usuario = usuario;
        }
    }
}
