/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import exceptions.BusinessLogicException;
import javax.ws.rs.core.GenericType;
import objects.Usuario;

/**
 *
 * @author bayron
 */
public interface UsuarioInterfaz {

    public <T> T signIn(GenericType<T> responseType, String emailUsr, String contraseniaUsr) throws BusinessLogicException;
    //public <T> T signIn(String emailUsr, String contraseniaUsr) throws BusinessLogicException;
}
