/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bussinesLogic.RecetaInterfaz;
import exceptions.BusinessLogicException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import objects.Receta;
import java.util.logging.Logger;

/**
 * Jersey REST client generated for REST resource:RecetaFacadeREST
 * [entidades.receta]<br>
 * USAGE:
 * <pre>
 *        RecetaFacadeREST client = new RecetaFacadeREST();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author 2dam
 */
public class RecetaFacadeREST implements RecetaInterfaz {

    private WebTarget webTarget;
    private Client client;
    private final ResourceBundle bundle = ResourceBundle.getBundle("files.URL");
    private final String BASE_URI = bundle.getString("BASE_URI");

    private Logger LOGGER = Logger.getLogger(RecetaFacadeREST.class.getName());

    public RecetaFacadeREST() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entidades.receta");
    }

    public void close() {
        client.close();
    }

    @Override
    public <T> T vegano(GenericType<T> respuesta, String esVegano) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("busquedaTelefono/{0}", new Object[]{esVegano}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se pudo encontrar ningun usuario con ese telefono");
        }
    }

    @Override
    public void updateReceta(Receta receta) throws BusinessLogicException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(receta, javax.ws.rs.core.MediaType.APPLICATION_XML), Receta.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error intentando editar el receta:" + ex.getMessage());
        }
    }

    @Override
    public void createReceta(Receta receta) throws BusinessLogicException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(receta, javax.ws.rs.core.MediaType.APPLICATION_XML), Receta.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al crear el cliente" + ex.getMessage());
        }
    }

    @Override
    public <T> T listaRecetas(GenericType<T> respuesta) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            LOGGER.log(Level.INFO, "URL de la solicitud: {0}", resource.getUri());
            int statusCode = resource.request().get().getStatus();
            LOGGER.log(Level.INFO, "Código de estado HTTP: {0}", statusCode);
            String responseContent = resource.request().get(String.class);
            LOGGER.log(Level.INFO, "Contenido de la respuesta: {0}", responseContent);
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al cargar los datos de los :" + ex.getMessage());
        }
    }

    @Override
    public <T> T vegetariano(GenericType<T> respuesta, String esVegetariano) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("busquedaTelefono/{0}", new Object[]{esVegetariano}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se pudo encontrar ningun usuario con ese telefono");
        }
    }

    @Override
    public <T> T precio(GenericType<T> respuesta, String precio) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("busquedaTelefono/{0}", new Object[]{precio}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se pudo encontrar ningun usuario con ese telefono");
        }
    }

    public void deleteReceta(Integer id) throws BusinessLogicException {
        try {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete(Receta.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al eliminar la receta" + ex.getMessage());
        }
    }

}
