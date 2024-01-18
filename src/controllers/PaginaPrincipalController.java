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

/**
 *
 * @author bayro
 */
public class PaginaPrincipalController {

    private Stage stage;

    MenuLateralControlller menuLateralController;

    @FXML
    private Button botonPrincipal, botonEjercicio, botonRecetas, botonPerfil, botonIngredientes;
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

        botonPerfil.setOnAction(
                this::abrirUsuarios);
        botonIngredientes.setOnAction(
                this::abrirIngredientes);

        // Inicializa el controlador del menú lateral antes de usarlo
        stage.show();
        LOGGER.info("Pagina principal iniciada");
    }

    private void abrirUsuarios(ActionEvent event) {
        try {
            LOGGER.info("Entrando");
            // Cambiar la lógica según lo que quieras hacer con el botón "Perfil"
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/administradorClientes.fxml"));
            Parent root = (Parent) loader.load();

            administradorClientesController controller = ((administradorClientesController) loader.getController());

            controller.setStage(stage);

            controller.initStage(root);
        } catch (IOException | IllegalStateException ex) {
            // Si hay un error al intentar cambiar la vista, muestra una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, "Error al intentar abrir la ventana de clientes: {0}", ex.getMessage());
        }
    }

    private void abrirIngredientes(ActionEvent event) {
        try {
            LOGGER.info("Entrando");
            // Cambiar la lógica según lo que quieras hacer con el botón "Perfil"
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/IngredientesAdmin.fxml"));
            Parent root = (Parent) loader.load();

            IngredientesAdminController controller = ((IngredientesAdminController) loader.getController());

            controller.setStage(stage);

            controller.initStage(root);
        } catch (IOException | IllegalStateException ex) {
            // Si hay un error al intentar cambiar la vista, muestra una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, "Error al intentar abrir la ventana de clientes: {0}", ex.getMessage());
        }
    }

}
