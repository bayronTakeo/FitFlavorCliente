/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bussinesLogic.UsuarioInterfaz;

import exceptions.BusinessLogicException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import java.util.logging.Logger;

/**
 *
 * @author bayro
 */
public class UsuarioFarcadeREST implements UsuarioInterfaz {

    private WebTarget webTarget;
    private Client client;
    private final ResourceBundle bundle = ResourceBundle.getBundle("files.URL");
    private final String BASE_URI = bundle.getString("BASE_URI");

    private Logger LOGGER = Logger.getLogger(UsuarioFarcadeREST.class.getName());

    public UsuarioFarcadeREST() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("usuario");
    }

    @Override
    public <T> T signIn(Class<T> responseType, String email, String contrasenia) throws BusinessLogicException {
        try {
            LOGGER.log(Level.INFO, "Intentando iniciar sesion");
            WebTarget resource = webTarget;
            LOGGER.log(Level.INFO, "URL de la solicitud: {0}", resource.getUri());
            int statusCode = resource.request().get().getStatus();
            LOGGER.log(Level.INFO, "Código de estado HTTP: {0}", statusCode);
            resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{email, contrasenia}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error durante el inicio de sesión", e);
            throw new BusinessLogicException("User not found");
        }
    }

}
