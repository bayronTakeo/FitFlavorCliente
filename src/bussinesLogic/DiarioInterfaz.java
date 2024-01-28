/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import exceptions.BusinessLogicException;
import javax.ws.rs.core.GenericType;
import objects.Diario;

/**
 *
 * @author bayro
 */
public interface DiarioInterfaz {

    public void crearIngrediente(Diario diario) throws BusinessLogicException;

    public <T> T findAll(GenericType<T> respuesta) throws BusinessLogicException;

    public <T> T buscarPorId(GenericType<T> respuesta, String id) throws BusinessLogicException;

    public void deleteIngrediente(Integer id) throws BusinessLogicException;
}
