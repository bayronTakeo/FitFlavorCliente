/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussinesLogic.DiarioFactory;
import bussinesLogic.EjercicioFactory;
import exceptions.BusinessLogicException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import objects.Cliente;
import objects.Diario;
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
    @FXML
    private TableColumn<Diario, Boolean> columnaAñadir;
    @FXML
    private DatePicker fechaDiario;

    private Usuario cliente;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsuario(Usuario cliente) {
        this.cliente = cliente;
    }
    private ObservableList<Ejercicio> informacionEjercicios;

    private ObservableList<Diario> informacionDiario;

    private Diario diario;

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Diario");
        stage.setResizable(false);

        fechaDiario.valueProperty().addListener(this::recogerDiario);

        //Cargo y configuro la tabla ejercicios
        tablaDiarioEjercicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        columnaDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
        columnaKcalQuemadas.setCellValueFactory(new PropertyValueFactory("kcalQuemadas"));
        /* columnaAñadir.setCellFactory(
                CheckBoxTableCell.<Diario>forTableColumn(columnaAñadir));
        columnaAñadir.setCellValueFactory(
                (TableColumn.CellDataFeatures<Ingredient, Boolean> param) -> param.getValue().getIsInSeasonProperty());
         */
        //Try para llamar al metodo que recoge los ejercicios
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

    public void recogerDiario(ObservableValue observable, LocalDate viejaFecha, LocalDate nuevaFecha) {
        if (nuevaFecha != null) {
            //Try para llamar al metodo que recoge el diario
            try {
                String fecha = fechaDiario.getValue().toString();

                diario = DiarioFactory.getModelo().buscarPorFecha(new GenericType<Diario>() {
                }, fecha, cliente.getUser_id());
                LOGGER.info(diario.toString());
            } catch (BusinessLogicException ex) {
                //Si algo va mal lo muestro en un alert
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
        }
    }

    public int verficiarEjercicio() {
        return 0;
    }
}
