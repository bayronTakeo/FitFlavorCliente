/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bussinesLogic.IngredienteInterfaz;
import exceptions.BusinessLogicException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import objects.Cliente;
import objects.Ingrediente;

/**
 * Jersey REST client generated for REST resource:IngredienteFacadeREST
 * [entidades.ingrediente]<br>
 * USAGE:
 * <pre>
 *        IngredienteRESTCliente client = new IngredienteRESTCliente();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Bayron
 */
public class IngredienteRESTCliente implements IngredienteInterfaz {

    private WebTarget webTarget;
    private Client client;
    private final ResourceBundle bundle = ResourceBundle.getBundle("files.URL");
    private final String BASE_URI = bundle.getString("BASE_URI");

    private Logger LOGGER = Logger.getLogger(IngredienteRESTCliente.class.getName());

    public IngredienteRESTCliente() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("ingrediente");
    }

    @Override
    public void crearIngrediente(Ingrediente ingrediente) throws BusinessLogicException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(ingrediente, javax.ws.rs.core.MediaType.APPLICATION_XML), Ingrediente.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al crear el ingrediente" + ex.getMessage());
        }
    }

    @Override
    public void updateIngrediente(Ingrediente ingrediente) throws BusinessLogicException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(ingrediente, javax.ws.rs.core.MediaType.APPLICATION_XML), Ingrediente.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error intentando editar el ingrediente:" + ex.getMessage());
        }
    }

    @Override
    public void deleteIngrediente(Integer id) throws BusinessLogicException {
        try {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete(Ingrediente.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al eliminar el ingrediente" + ex.getMessage());
        }
    }

    @Override
    public <T> T buscarPorId(GenericType<T> respuesta, String id) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se encontro ningun ingrediente" + ex.getMessage());
        }
    }

    public void close() {
        client.close();
    }
}
