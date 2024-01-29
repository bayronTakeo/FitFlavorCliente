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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TableView<Ejercicio> tablaDiarioEjercicios;

    @FXML
    private TableColumn<Ejercicio, String> columnaNombre;
    @FXML
    private TableColumn<Ejercicio, String> columnaDescripcion;
    @FXML
    private TableColumn<Ejercicio, Float> columnaDuracion;
    @FXML
    private TableColumn<Ejercicio, String> columnaKcalQuemadas;
    @FXML
    private TableColumn<Ejercicio, Boolean> columnaAñadir;
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

    private List<Ejercicio> ejerciciosEnDiario;
    private Diario diario = new Diario();

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Diario");
        stage.setResizable(false);

        ejerciciosEnDiario = new ArrayList<>();

        fechaDiario.valueProperty().addListener(this::recogerDiario);

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

        //Cargo y configuro la tabla ejercicios
        tablaDiarioEjercicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        columnaDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
        columnaKcalQuemadas.setCellValueFactory(new PropertyValueFactory("kcalQuemadas"));
        columnaAñadir.setCellValueFactory(param -> {
            SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(param.getValue() != null && diario != null && diario.getListaEjercicios() != null
                    && diario.getListaEjercicios().stream().anyMatch(infoEjercicio -> param.getValue().getId().equals(infoEjercicio.getId())));
            Ejercicio ejercicio = param.getValue();
            if (booleanProperty.getValue()) {
                ejerciciosEnDiario.add(ejercicio);
            }
            booleanProperty.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {

                LOGGER.info("Estado del checkbox para el ejercicio " + ejercicio.getNombre() + ": " + newValue);
                try {
                    if (newValue) {

                        ejerciciosEnDiario.add(ejercicio);
                        diario.setListaEjercicios(ejerciciosEnDiario);
                        diario.setComentarios("prueba");
                        DiarioFactory.getModelo().actualizarDiario(diario);

                    } else {
                        tablaDiarioEjercicios.refresh();
                        LOGGER.info("Entra al else ");
                        ejerciciosEnDiario.remove(ejercicio);
                        DiarioFactory.getModelo().actualizarDiario(diario);
                    }
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(DiarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            return booleanProperty;
        });

        // Configura el CellFactory para la columna
        columnaAñadir.setCellFactory(CheckBoxTableCell.forTableColumn(columnaAñadir));

        //Añado los objetos ejercicios a la tabla
        tablaDiarioEjercicios.setItems(informacionEjercicios);
        //Hago la tabla editable
        tablaDiarioEjercicios.setEditable(true);

        //Logica para cuando el checkbox se clicke o desclicke
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
                LOGGER.info("info del diario: " + diario.toString());
                tablaDiarioEjercicios.refresh();
            } catch (BusinessLogicException ex) {
                diario = new Diario();
                tablaDiarioEjercicios.refresh();
            }
        }
    }

    public Diario buscarEjercicios(Integer id) {
        //Try para llamar al metodo que recoge el diario
        try {
            String fecha = fechaDiario.getValue().toString();

            diario = DiarioFactory.getModelo().buscarEjercicio(new GenericType<Diario>() {
            }, fecha, cliente.getUser_id(), id);
            LOGGER.info(diario.toString());
        } catch (BusinessLogicException ex) {
            //Si algo va mal lo muestro en un alert
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return diario;
    }

    public int verficiarEjercicio() {
        return 0;
    }
}
