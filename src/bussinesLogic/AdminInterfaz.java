/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import exceptions.BusinessLogicException;
import objects.Admin;

/**
 *
 * @author Bayron
 */
public interface AdminInterfaz {

    public void crearAdmin(Admin admin) throws BusinessLogicException;
}
