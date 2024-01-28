/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussinesLogic.EjercicioFactory;
import exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import objects.Cliente;
import objects.Ejercicio;
import objects.Usuario;

/**
 *
 * @author bayro
 */
public class DiarioController {

    private Stage stage;

    private static final Logger LOGGER = Logger.getLogger("DiarioController.class");

    @FXML
    private TableView tablaDiarioEjercicios;
    @FXML
    private TableColumn<Ejercicio, String> columnaNombre;
    @FXML
    private TableColumn<Ejercicio, String> columnaDescripcion;
    @FXML
    private TableColumn<Ejercicio, Float> columnaDuracion;
    @FXML
    private TableColumn<Ejercicio, String> columnaKcalQuemadas;
    private Usuario cliente;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsuario(Usuario cliente) {
        this.cliente = cliente;
    }
    private ObservableList<Ejercicio> informacionEjercicios;

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Diario");
        stage.setResizable(false);

        //Cargo y configuro la tabla ejercicios
        tablaDiarioEjercicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        columnaDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
        columnaKcalQuemadas.setCellValueFactory(new PropertyValueFactory("kcalQuemadas"));

        try {
            //Recojo los datos de ejercicios y los guardo en el observableList
            informacionEjercicios = FXCollections.observableArrayList(EjercicioFactory.getModelo().findAll(new GenericType<List<Ejercicio>>() {
            }));
        } catch (BusinessLogicException ex) {
            //Si algo va mal lo muestro en un alert
            Alert alert = new Alert(Alert.AlertType.ERROR, "La informacion no ha podido ser cargada.");
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaDiarioEjercicios.refresh();
        }
        //Añado los objetos ejercicios a la tabla
        tablaDiarioEjercicios.setItems(informacionEjercicios);
        //Hago la tabla editable
        tablaDiarioEjercicios.setEditable(true);
        stage.show();
        LOGGER.info("Diario iniciado!");
    }
}
