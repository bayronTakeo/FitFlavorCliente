/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bussinesLogic.EjercicioInterfaz;
import exceptions.BusinessLogicException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import objects.Ejercicio;
import objects.TipoIntensidad;

/**
 * Jersey REST client generated for REST resource:EjercicioFacadeREST
 * [entidades.ejercicio]<br>
 * USAGE:
 * <pre>
 *        EjercicioRestCliente client = new EjercicioRestCliente();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author gaizka
 */
public class EjercicioRESTCliente implements EjercicioInterfaz{

    private WebTarget webTarget;
    private Client client;
    private final ResourceBundle bundle = ResourceBundle.getBundle("files.URL");
    private final String BASE_URI = bundle.getString("BASE_URI");
    
    private Logger LOGGER = Logger.getLogger(UsuarioRESTCliente.class.getName());
    
    public EjercicioRESTCliente() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entidades.ejercicio");
    }

    @Override
    public void crearEjercicio(Ejercicio ejercicio) throws BusinessLogicException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(ejercicio, javax.ws.rs.core.MediaType.APPLICATION_XML), Ejercicio.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al crear el ejercicio");
        }
    }
    
    @Override
    public void actualizarEjercicio(Ejercicio ejercicio) throws BusinessLogicException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(ejercicio, javax.ws.rs.core.MediaType.APPLICATION_XML), Ejercicio.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error intentando editar el ejercicio:" + ex.getMessage());
        }
    }
    
    @Override
    public void eliminarEjercicio(Integer id) throws BusinessLogicException {
        try {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete(Ejercicio.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al eliminar el ejercicio" + ex.getMessage());
        }
    }
    
    @Override
    public <T> T buscarPorId(GenericType<T> respuesta,String id) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se encontro ningun ejercicio." + ex.getMessage());
        }
    }

    @Override
    public <T> T listaPorTipo(GenericType<T> respuesta, String tipo) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("busquedaTipo/{0}", new Object[]{tipo}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se pudo encontrar ningun ejercicio de ese tipo");
        }    
    }
    
    public <T> T listaIntensidad(GenericType<T> respuesta, String tipoIntensidad) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("busquedaIntensidad/{0}", new Object[]{tipoIntensidad}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se pudo encontrar ningun ejercicio con ese nivel de intensidad");
        }    
    }

    @Override
    public <T> T findAll(GenericType<T> respuesta) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            LOGGER.log(Level.INFO, "URL de la solicitud: {0}", resource.getUri());
            int statusCode = resource.request().get().getStatus();
            LOGGER.log(Level.INFO, "Código de estado HTTP: {0}", statusCode);
            String responseContent = resource.request().get(String.class);
            LOGGER.log(Level.INFO, "Contenido de la respuesta: {0}", responseContent);
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al cargar los datos de los ejercicios:" + ex.getMessage());
        }    
    }
    
    public void close() {
        client.close();
    }

}