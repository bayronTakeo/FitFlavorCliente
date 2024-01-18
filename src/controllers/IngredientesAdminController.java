/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.util.Duration;
import java.util.Date;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import objects.Cliente;

/**
 *
 * @author bayro
 */
public class IngredientesAdminController {

    private Stage stage;
    private static final Logger LOGGER = Logger.getLogger("IngredientesAdminController.class");

    @FXML
    private Pane paneFiltrar;
    @FXML
    private AnchorPane p;
    @FXML
    private Button botonFiltros, botonCancelar;
    @FXML
    private TableView tablaIngredientes;
    @FXML
    private TableColumn<Cliente, String> columnaTipo;
    @FXML
    private TableColumn<Cliente, String> columnaNombre;
    @FXML
    private TableColumn<Cliente, Date> columnafecha;
    @FXML
    private TableColumn<Cliente, String> columnaTelefono;
    @FXML
    private TableColumn<Cliente, String> columnaDireccion;
    @FXML
    private TableColumn<Cliente, String> columnaCodPostal;
    @FXML
    private TableColumn<Cliente, String> columnaContrania;

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

        botonFiltros.setOnAction(
                this::abrirMenuFiltros);
        botonCancelar.setOnAction(
                this::cerrarMenuFiltros);

        // Inicializa el controlador del menú lateral antes de usarlo
        stage.show();
        LOGGER.info("Pagina principal iniciada");
    }

    private void abrirMenuFiltros(ActionEvent event) {
        botonFiltros.setVisible(false);
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(paneFiltrar);

        slide.setToX(-286);
        slide.play();
        slide.setOnFinished((ActionEvent e) -> {

        });
        LOGGER.info("aqui llega");

    }

    private void cerrarMenuFiltros(ActionEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(paneFiltrar);

        slide.setToX(286);
        slide.play();
        slide.setOnFinished((ActionEvent e) -> {
            botonFiltros.setVisible(true);
        });
        LOGGER.info("aqui llega");

    }
}
