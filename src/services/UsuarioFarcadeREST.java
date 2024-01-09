/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bussinesLogic.UsuarioInterfaz;
import exceptions.BusinessLogicException;

/**
 *
 * @author bayro
 */
public class UsuarioFarcadeREST implements UsuarioInterfaz {

    @Override
    public <T> T signIn(Class<T> responseType, String email, String contrasenia) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
