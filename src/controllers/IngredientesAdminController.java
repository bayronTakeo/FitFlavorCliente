/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussinesLogic.ClienteFactory;
import bussinesLogic.IngredienteFactory;
import exceptions.BusinessLogicException;
import java.text.SimpleDateFormat;
import javafx.util.Duration;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import objects.Cliente;
import objects.Ingrediente;
import objects.TipoIngrediente;

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
    private TableColumn<Cliente, TipoIngrediente> columnaTipo;
    @FXML
    private TableColumn<Cliente, String> columnaNombre;
    @FXML
    private TableColumn<Cliente, Float> columnaPrecio;
    @FXML
    private TableColumn<Cliente, Float> columnaKcal;
    @FXML
    private TableColumn<Cliente, Float> columnaCarb;
    @FXML
    private TableColumn<Cliente, Float> columnaProteinas;
    @FXML
    private TableColumn<Cliente, Float> columnaGrasas;

    private ObservableList<Ingrediente> informacionIngredientes;

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

        tablaIngredientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaTipo.setCellValueFactory(new PropertyValueFactory("tipoIngrediente"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        columnaKcal.setCellValueFactory(new PropertyValueFactory("kCal"));
        columnaCarb.setCellValueFactory(new PropertyValueFactory("carbohidratos"));
        columnaProteinas.setCellValueFactory(new PropertyValueFactory("proteinas"));
        columnaGrasas.setCellValueFactory(new PropertyValueFactory("grasas"));

        stage.show();
        try {

            informacionIngredientes = FXCollections.observableArrayList(IngredienteFactory.getModelo().findAll(new GenericType<List<Ingrediente>>() {
            }));
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La informacion no ha podido ser cargada.");
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaIngredientes.refresh();
        }
        tablaIngredientes.setItems(informacionIngredientes);
        tablaIngredientes.setEditable(true);

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
