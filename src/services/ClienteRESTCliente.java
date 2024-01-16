/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bussinesLogic.ClienteInterfaz;
import exceptions.BusinessLogicException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import objects.Cliente;

/**
 * Jersey REST client generated for REST resource:ClienteFacadeREST
 * [entidades.cliente]<br>
 * USAGE:
 * <pre>
 *        ClienteREST client = new ClienteREST();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author bayro
 */
public class ClienteRESTCliente implements ClienteInterfaz {

    private WebTarget webTarget;
    private Client client;
    private final ResourceBundle bundle = ResourceBundle.getBundle("files.URL");
    private final String BASE_URI = bundle.getString("BASE_URI");

    private Logger LOGGER = Logger.getLogger(UsuarioRESTCliente.class.getName());

    public ClienteRESTCliente() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("cliente");
    }

    @Override
    public void crearCliente(Cliente cliente) throws BusinessLogicException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(cliente, javax.ws.rs.core.MediaType.APPLICATION_XML), Cliente.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al crear el cliente" + ex.getMessage());
        }
    }

    @Override
    public void actualizarCliente(Cliente cliente) throws BusinessLogicException {
        try {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(cliente, javax.ws.rs.core.MediaType.APPLICATION_XML), Cliente.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error intentando editar el cliente:" + ex.getMessage());
        }
    }

    @Override
    public void eliminarCliente(Integer id) throws BusinessLogicException {
        try {
            webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete(Cliente.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al eliminar el cliente" + ex.getMessage());
        }
    }

    @Override
    public <T> T findAll(GenericType<T> respuesta) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al cargar los datos de los clientes:" + ex.getMessage());
        }
    }

    @Override
    public <T> T buscarPorTelefono(GenericType<T> respuesta, int telefono) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("busquedaTelefono/{0}", new Object[]{telefono}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se pudo encontrar ningun usuario con ese telefono");
        }
    }

    @Override
    public <T> T buscarCliente(GenericType<T> respuesta, String valor) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("busqueda/{0}", new Object[]{valor}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se encontro ningun cliente.");
        }
    }

    @Override
    public <T> T buscarPorId(GenericType<T> respuesta, String id) throws BusinessLogicException {
        try {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(respuesta);
        } catch (Exception ex) {
            throw new BusinessLogicException("No se encontro ningun cliente" + ex.getMessage());
        }
    }

    public void close() {
        client.close();
    }

}