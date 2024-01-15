/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import exceptions.BusinessLogicException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author paula
 */
public interface RecetaInterfaz {

    public <T> T lista(Class<T> responseType) throws BusinessLogicException;

    public <T> T vegano(Class<T> responseType, String esVegano) throws BusinessLogicException;

    public <T> T vegetariano_XML(Class<T> responseType, String esVegetariano) throws BusinessLogicException;

    public <T> T precio_XML(Class<T> responseType, String precio) throws BusinessLogicException;

    public void edit_XML(Object requestEntity) throws BusinessLogicException;

    public void create_XML(Object requestEntity) throws BusinessLogicException;
    

}
