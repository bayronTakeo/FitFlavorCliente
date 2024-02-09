/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import services.AdminRESTCiente;

/**
 *
 * @author Bayron
 */
public class AdminFactory {

    public static AdminInterfaz modelo;

    public static AdminInterfaz getModelo() {
        if (modelo == null) {
            modelo = new AdminRESTCiente();
        }
        return modelo;
    }
}
