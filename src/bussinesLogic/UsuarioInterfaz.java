/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import exceptions.BusinessLogicException;

/**
 *
 * @author bayron
 */
public interface UsuarioInterfaz {

    public <T> T signIn(Class<T> responseType, String email, String contrasenia) throws BusinessLogicException;
}
