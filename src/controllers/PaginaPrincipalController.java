/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.Cliente;

/**
 *
 * @author bayro
 */
public class PaginaPrincipalController {

    private Stage stage;

    private SesionCliente sesionCliente = SesionCliente.getInstance();

    Cliente cliente = sesionCliente.getCliente();

    @FXML
    private final MenuLateralControlller menulateralController = new MenuLateralControlller();

    private static final Logger LOGGER = Logger.getLogger("PaginaPrincipalController.class");

    public void setSesionCliente(SesionCliente sesionCliente) {
        this.sesionCliente = sesionCliente;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pagina Principal");
        stage.setResizable(false);
        LOGGER.info("asdasdas" + cliente.toString());
        stage.show();
        LOGGER.info("Pagina principal iniciada");
    }

}
