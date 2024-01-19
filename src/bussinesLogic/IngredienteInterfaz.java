/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import exceptions.BusinessLogicException;
import javax.ws.rs.core.GenericType;
import objects.Ingrediente;
import objects.TipoIngrediente;

/**
 *
 * @author Bayron
 */
public interface IngredienteInterfaz {

    public void crearIngrediente(Ingrediente ingrediente) throws BusinessLogicException;

    public void updateIngrediente(Ingrediente ingrediente) throws BusinessLogicException;

    public void deleteIngrediente(Integer id) throws BusinessLogicException;

    public <T> T buscarPorId(GenericType<T> respuesta, String id) throws BusinessLogicException;

    public <T> T findAll(GenericType<T> respuesta) throws BusinessLogicException;

    public <T> T buscarFiltros(GenericType<T> respuesta, TipoIngrediente tipoIngrediente, String nombre, Float precio, Float kcal, Float carb, Float proteina, Float grasas) throws BusinessLogicException;
}
