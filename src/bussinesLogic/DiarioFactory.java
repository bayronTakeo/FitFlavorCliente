/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import services.DiarioRESTCliente;

/**
 *
 * @author bayro
 */
public class DiarioFactory {

    public static DiarioInterfaz modelo;

    public static DiarioInterfaz getModelo() {
        if (modelo == null) {
            modelo = new DiarioRESTCliente();
        }
        return modelo;
    }
}
