/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bussinesLogic.AdminInterfaz;
import exceptions.BusinessLogicException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import objects.Admin;

/**
 * Jersey REST client generated for REST resource:AdminFacadeREST
 * [entidades.admin]<br>
 * USAGE:
 * <pre>
 *        AdminRESTCiente client = new AdminRESTCiente();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Bayron
 */
public class AdminRESTCiente implements AdminInterfaz {

    private WebTarget webTarget;
    private Client client;
    private final ResourceBundle bundle = ResourceBundle.getBundle("files.URL");
    private final String BASE_URI = bundle.getString("BASE_URI");

    private Logger LOGGER = Logger.getLogger(UsuarioRESTCliente.class.getName());

    public AdminRESTCiente() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entidades.admin");
    }

    @Override
    public void crearAdmin(Admin admin) throws BusinessLogicException {
        try {

            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(admin, javax.ws.rs.core.MediaType.APPLICATION_XML), Admin.class);
        } catch (Exception ex) {
            throw new BusinessLogicException("Ha ocurrido un error al crear el admin" + ex.getMessage());
        }
    }

}
