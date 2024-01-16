/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import services.UsuarioRESTCliente;

/**
 *
 * @author bayron
 */
public class UsuarioFactory {

    public static UsuarioInterfaz modelo;

    public static UsuarioInterfaz getModelo() {
        if (modelo == null) {
            modelo = new UsuarioRESTCliente();
        }
        return modelo;
    }
}
