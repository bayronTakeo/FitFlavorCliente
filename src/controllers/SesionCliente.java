/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import objects.Cliente;
import objects.Usuario;

/**
 *
 * @author Bayron
 */
public class SesionCliente {

    private static SesionCliente instance = null;

    private Usuario cliente;

    public static SesionCliente getInstance() {
        if (instance == null) {
            instance = new SesionCliente();
        }
        return instance;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getCliente() {
        return cliente;
    }
}
