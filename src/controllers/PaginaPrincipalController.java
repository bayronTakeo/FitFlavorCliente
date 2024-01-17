/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author bayro
 */
public class PaginaPrincipalController {

    private Stage stage;

    MenuLateralControlller menuLateralController;

    private static final Logger LOGGER = Logger.getLogger("PaginaPrincipalController.class");

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pagina Principal");
        stage.setResizable(false);

        // Inicializa el controlador del menú lateral antes de usarlo
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/MenuLateral.fxml"));
        try {
            LOGGER.info("entra:))");
            Pane menuLateralPane = loader.load();
            menuLateralController = loader.getController(); // Asigna el controlador
            menuLateralController.setStage(stage);  // Asigna el Stage al controlador del menú lateral
            menuLateralController.initStage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.show();
        LOGGER.info("Pagina principal iniciada");
    }

}
