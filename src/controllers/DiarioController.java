/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.Cliente;
import objects.Usuario;

/**
 *
 * @author bayro
 */
public class DiarioController {

    private Stage stage;

    private static final Logger LOGGER = Logger.getLogger("DiarioController.class");

    private Usuario cliente;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsuario(Usuario cliente) {
        this.cliente = cliente;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Diario");
        stage.setResizable(false);

        stage.show();
        LOGGER.info("Diario iniciado!");
    }
}
