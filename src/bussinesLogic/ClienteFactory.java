/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import services.ClienteRESTCliente;

/**
 *
 * @author bayron
 */
public class ClienteFactory {

    public static ClienteInterfaz modelo;

    public static ClienteInterfaz getModelo() {
        if (modelo == null) {
            modelo = new ClienteRESTCliente();
        }
        return modelo;
    }
}
