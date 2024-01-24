/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import services.RecetaFacadeREST;

/**
 *
 * @author paula
 */
public class RecetaFactory {

    public static RecetaInterfaz modelo;

    public static RecetaInterfaz getModelo() {
        if (modelo == null) {
            modelo = (RecetaInterfaz) new RecetaFacadeREST();
        }
        return modelo;
    }
}
