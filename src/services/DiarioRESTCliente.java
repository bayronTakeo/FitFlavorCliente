/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bussinesLogic.DiarioInterfaz;
import exceptions.BusinessLogicException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import objects.Diario;

/**
 * Jersey REST client generated for REST resource:DiarioFacadeREST
 * [entidades.diario]<br>
 * USAGE:
 * <pre>
 *        DiarioRESTCliente client = new DiarioRESTCliente();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author bayro
 */
public class DiarioRESTCliente implements DiarioInterfaz {

    private WebTarget webTarget;
    private Client client;
    private final ResourceBundle bundle = ResourceBundle.getBundle("files.URL");
    private final String BASE_URI = bundle.getString("BASE_URI");

    private Logger LOGGER = Logger.getLogger(DiarioRESTCliente.class.getName());

    public DiarioRESTCliente() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entidades.diario");
    }

    @Override
    public void crearIngrediente(Diario diario) throws BusinessLogicException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(diario, javax.ws.rs.core.MediaType.APPLICATION_XML), Diario.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al crear el diario" + ex.getMessage());
        }
    }

    @Override
    public <T> T findAll(GenericType<T> respuesta) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            LOGGER.info("URL de la solicitud: {0}" + resource.getUri().toString());
            int statusCode = resource.request().get().getStatus();
            LOGGER.info("Código de estado HTTP: {0}" + statusCode);
            String responseContent = resource.request().get(String.class);
            LOGGER.log(Level.INFO, "Contenido de la respuesta diarios: ", responseContent);
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al cargar los datos de los diarios:" + ex.getMessage());
        }
    }

    @Override
    public <T> T buscarPorId(GenericType<T> respuesta, String id) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se encontro ningun diario" + ex.getMessage());
        }
    }

    @Override
    public void deleteIngrediente(Integer id) throws BusinessLogicException {
        try {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete(Diario.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al eliminar el diario: " + ex.getMessage());
        }
    }

    @Override
    public <T> T buscarPorFecha(GenericType<T> respuesta, String fecha, Integer cliente) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("buscarPorFecha/{0}/{1}", new Object[]{fecha, cliente}));
            String responseContent = resource.request().get(String.class);
            LOGGER.log(Level.INFO, "Contenido de la respuesta: {0}", responseContent);
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se encontró ningún diario: " + ex.getMessage());
        }
    }

    public void close() {
        client.close();
    }

    @Override
    public <T> T buscarEjercicio(GenericType<T> respuesta, String fecha, Integer cliente, Integer ejercicio) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("buscarEjercicio/{0}/{1}/{2}", new Object[]{fecha, cliente, ejercicio}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se encontró ningún diario: " + ex.getMessage());
        }
    }

    @Override
    public void actualizarDiario(Diario diario) throws BusinessLogicException {
        try {
            LOGGER.info("Entrando a editar diario: " + diario.toString());
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(diario, javax.ws.rs.core.MediaType.APPLICATION_XML), Diario.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error intentando editar el cliente:" + ex.getMessage());
        }
    }

}
