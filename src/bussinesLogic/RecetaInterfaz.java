/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import exceptions.BusinessLogicException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import objects.Receta;

/**
 *
 * @author paula
 */
public interface RecetaInterfaz {

    public <T> T listaRecetas(GenericType<T> respuesta) throws BusinessLogicException;

    public <T> T vegano(GenericType<T> respuesta, String esVegano) throws BusinessLogicException;

    public <T> T vegetariano(GenericType<T> respuesta, String esVegetariano) throws BusinessLogicException;

    public <T> T precio(GenericType<T> respuesta, String precio) throws BusinessLogicException;

    public void updateReceta(Receta receta) throws BusinessLogicException;

    public void createReceta(Receta receta) throws BusinessLogicException;

    public void deleteReceta(Integer id) throws BusinessLogicException;

}
