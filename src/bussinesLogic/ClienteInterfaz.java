/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussinesLogic;

import exceptions.BusinessLogicException;
import javax.ws.rs.core.GenericType;
import objects.Cliente;

/**
 *
 * @author bayron
 */
public interface ClienteInterfaz {

    public <T> T buscarPorId(GenericType<T> responseType, String id) throws BusinessLogicException;

    public void crearCliente(Cliente cliente) throws BusinessLogicException;

    public void actualizarCliente(Cliente cliente) throws BusinessLogicException;

    public void eliminarCliente(Integer id) throws BusinessLogicException;

    public <T> T findAll(GenericType<T> respuesta) throws BusinessLogicException;

    public <T> T buscarPorTelefono(GenericType<T> respuesta, int telefono) throws BusinessLogicException;

    public <T> T buscarCliente(GenericType<T> respuesta, String usrValor) throws BusinessLogicException;

    public void recuperarContrasenia(Cliente cliente) throws BusinessLogicException;

    public void actualizarContraseña(Cliente cliente) throws BusinessLogicException;

    public <T> T buscarNombre(GenericType<T> responseType, String nombre) throws BusinessLogicException;
}
