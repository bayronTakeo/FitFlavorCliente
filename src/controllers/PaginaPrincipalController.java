/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import objects.Cliente;

/**
 *
 * @author bayro
 */
public class PaginaPrincipalController {

    private Stage stage;

    private Cliente cliente;

    @FXML
    private MenuLateralControlller menulateralController = new MenuLateralControlller();

    private static final Logger LOGGER = Logger.getLogger("PaginaPrincipalController.class");

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pagina Principal");
        stage.setResizable(false);
        LOGGER.info("AQui llega el cliente bien: " + cliente.getEmail());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/MenuLateralAdmin.fxml"));

            Parent root2 = (Parent) loader.load();
            MenuLateralControlller controller = ((MenuLateralControlller) loader.getController());
            controller.setCliente(cliente);
        } catch (IOException ex) {
            Logger.getLogger(PaginaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        menulateralController.setCliente(cliente);

        stage.show();
        LOGGER.info("Pagina principal iniciada");
    }

}
