/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import exceptions.BusinessLogicException;
import javax.ws.rs.core.GenericType;
import objects.Ejercicio;

/**
 *
 * @author gaizka
 */
public interface EjercicioInterfaz {
    
    public void crearEjercicio(Ejercicio ejercicio) throws BusinessLogicException;

    public <T> T listaPorTipo(GenericType<T> respuesta, String tipo) throws BusinessLogicException;

    public <T> T listaIntensidad(GenericType<T> respuesta, String intensidad) throws BusinessLogicException;

    public void actualizarEjercicio(Ejercicio ejercicio) throws BusinessLogicException;

    public void eliminarEjercicio(Integer id) throws BusinessLogicException;

    public <T> T findAll(GenericType<T> respuesta) throws BusinessLogicException;

    public <T> T buscarPorId(GenericType<T> respuesta, String id) throws BusinessLogicException;
    
}
