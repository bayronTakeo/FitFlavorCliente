/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import services.EjercicioRESTCliente;

/**
 *
 * @author gaizka
 */
public class EjercicioFactory {
    
    public static EjercicioInterfaz modelo;

    public static EjercicioInterfaz getModelo() {
        if (modelo == null) {
            modelo = new EjercicioRESTCliente();
        }
        return modelo;
    }
}
