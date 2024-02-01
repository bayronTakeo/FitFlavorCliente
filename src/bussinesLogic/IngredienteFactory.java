/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import services.IngredienteRESTCliente;

/**
 *
 * @author Bayron
 */
public class IngredienteFactory {

    public static IngredienteInterfaz modelo;

    public static IngredienteInterfaz getModelo() {
        if (modelo == null) {
            modelo = new IngredienteRESTCliente();
        }
        return modelo;
    }
}
