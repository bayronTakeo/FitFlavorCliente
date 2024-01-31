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
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javax.ws.rs.core.GenericType;
import logicaTablas.floatFormateador;
import objects.Admin;
import objects.Ejercicio;
import objects.TipoEjercicio;
import objects.TipoIntensidad;
import objects.Usuario;

/**
 *
 * @author gaizka
 */
public class EjercicioAdminController {

    private Stage stage;

    private static final Logger LOGGER = Logger.getLogger("EjercicioAdminController.class");

    private Usuario usuario;

    @FXML
    private Pane paneFiltrar;
    @FXML
    private ContextMenu menuTabla;
    @FXML
    private TableView tablaEjercicios;
    @FXML
    private TableColumn<Ejercicio, String> columnaNombre;
    @FXML
    private TableColumn<Ejercicio, TipoEjercicio> columnaTipoEjercicio;
    @FXML
    private TableColumn<Ejercicio, String> columnaDescripcion;
    @FXML
    private TableColumn<Ejercicio, Float> columnaDuracion;
    @FXML
    private TableColumn<Ejercicio, String> columnaKcalQuemadas;
    @FXML
    private TableColumn<Ejercicio, TipoIntensidad> columnaTipoIntensidad;
    @FXML
    private ComboBox comboTipo;
    @FXML
    private ComboBox comboIntensidad;
    @FXML
    private Spinner spinnerDuracion;
    @FXML
    private Button botonEditar, botonAgregar, botonEliminar, botonFiltros, botonCerrar, botonAplicar;

    private ObservableList<Ejercicio> informacionEjercicios;

    private ObservableList<TipoEjercicio> opciones
            = FXCollections.observableArrayList(TipoEjercicio.values());

    private ObservableList<TipoIntensidad> opcionesIntensidad
            = FXCollections.observableArrayList(TipoIntensidad.values());

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Administracion Ejercicios");
        stage.setResizable(false);
        stage.setOnCloseRequest(this::handleExitAction);
        botonFiltros.setOnAction(
                this::abrirMenuFiltros);
        botonCerrar.setOnAction(
                this::cerrarMenuFiltros);

        tablaEjercicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaTipoEjercicio.setCellValueFactory(new PropertyValueFactory("tipoEjercicio"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        columnaDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
        columnaKcalQuemadas.setCellValueFactory(new PropertyValueFactory("kcalQuemadas"));
        columnaTipoIntensidad.setCellValueFactory(new PropertyValueFactory("tipoIntensidad"));

        try {

            informacionEjercicios = FXCollections.observableArrayList(EjercicioFactory.getModelo().findAll(new GenericType<List<Ejercicio>>() {
            }));
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La informacion no ha podido ser cargada.");
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaEjercicios.refresh();
        }

        tablaEjercicios.setItems(informacionEjercicios);
        tablaEjercicios.setEditable(true);

        //Editar columna nombre
        columnaNombre.setCellFactory(TextFieldTableCell.<Ejercicio>forTableColumn());
        columnaNombre.setOnEditCommit(
                (TableColumn.CellEditEvent<Ejercicio, String> t) -> {
                    try {
                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNombre(t.getNewValue());
                        EjercicioFactory.getModelo().actualizarEjercicio((Ejercicio) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNombre(t.getOldValue());
                        tablaEjercicios.refresh();
                    }

                }
        );

        //Editar columna tipoEjercicio
        columnaTipoEjercicio.setCellFactory(ComboBoxTableCell.<Ejercicio, TipoEjercicio>forTableColumn(TipoEjercicio.values()));
        columnaTipoEjercicio.setOnEditCommit(
                (CellEditEvent<Ejercicio, TipoEjercicio> t) -> {

                    Ejercicio tipoSeleccionado = (Ejercicio) tablaEjercicios.getSelectionModel().getSelectedItem();
                    TipoEjercicio valorOriginal = t.getOldValue();
                    ComboBox<TipoEjercicio> comboBox = new ComboBox<>();
                    comboBox.setItems(opciones);
                    try {
                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTipoEjercicio(t.getNewValue());
                        EjercicioFactory.getModelo().actualizarEjercicio((Ejercicio) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {

                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTipoEjercicio(valorOriginal);
                        tablaEjercicios.refresh();
                    }
                });
        //Editar columna Descripcion
        columnaDescripcion.setCellFactory(TextFieldTableCell.<Ejercicio>forTableColumn());
        columnaDescripcion.setOnEditCommit(
                (TableColumn.CellEditEvent<Ejercicio, String> t) -> {
                    try {
                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setDescripcion(t.getNewValue());
                        EjercicioFactory.getModelo().actualizarEjercicio((Ejercicio) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setDescripcion(t.getOldValue());
                        tablaEjercicios.refresh();
                    }

                }
        );

        //Editar columna Duracion
        columnaDuracion.setCellFactory(TextFieldTableCell.<Ejercicio, Float>forTableColumn(new floatFormateador()));
        columnaDuracion.setOnEditCommit((CellEditEvent<Ejercicio, Float> t) -> {
            Ejercicio seleccionado = (Ejercicio) tablaEjercicios.getSelectionModel().getSelectedItem();
            Float duracion = seleccionado.getDuracion();
            try {
                if (t.getNewValue() <= 9999) {
                    ((Ejercicio) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setDuracion(t.getNewValue());
                    EjercicioFactory.getModelo().actualizarEjercicio((Ejercicio) t.getTableView().getSelectionModel().getSelectedItem());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "El número maximo posible es de 4 digitos!");
                    alert.show();
                    informacionEjercicios = FXCollections.observableArrayList(EjercicioFactory.getModelo().findAll(new GenericType<List<Ejercicio>>() {
                    }));
                    tablaEjercicios.setItems(informacionEjercicios);
                }
            } catch (BusinessLogicException ex) {
                ((Ejercicio) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setDuracion(duracion);
                tablaEjercicios.refresh();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error en el servidor" + ex.getMessage());
                alert.show();
                LOGGER.log(Level.SEVERE, "Error al intentar actualizar", ex.getMessage());
            } catch (NullPointerException ex) {
                ((Ejercicio) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setDuracion(t.getOldValue());
                tablaEjercicios.refresh();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Este campo solo admite números!");
                alert.show();
                LOGGER.log(Level.SEVERE, "Error al intentar actualizar", ex.getMessage());
            }
        });
        //Editar columna KcalQuemadas
        columnaKcalQuemadas.setCellFactory(TextFieldTableCell.<Ejercicio>forTableColumn());
        columnaKcalQuemadas.setOnEditCommit(
                (TableColumn.CellEditEvent<Ejercicio, String> t) -> {
                    try {
                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setKcalQuemadas(t.getNewValue());
                        EjercicioFactory.getModelo().actualizarEjercicio((Ejercicio) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setKcalQuemadas(t.getOldValue());
                        tablaEjercicios.refresh();
                    }

                }
        );

        //Editar columna TipoIntensidad
        columnaTipoIntensidad.setCellFactory(ComboBoxTableCell.<Ejercicio, TipoIntensidad>forTableColumn(TipoIntensidad.values()));
        columnaTipoIntensidad.setOnEditCommit(
                (CellEditEvent<Ejercicio, TipoIntensidad> t) -> {

                    Ejercicio tipoSeleccionado = (Ejercicio) tablaEjercicios.getSelectionModel().getSelectedItem();
                    TipoIntensidad valorOriginal = t.getOldValue();
                    ComboBox<TipoIntensidad> comboBox = new ComboBox<>();
                    comboBox.setItems(opcionesIntensidad);
                    try {
                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTipoIntensidad(t.getNewValue());
                        EjercicioFactory.getModelo().actualizarEjercicio((Ejercicio) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {

                        ((Ejercicio) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTipoIntensidad(valorOriginal);
                        tablaEjercicios.refresh();
                    }
                });
        //Editar un ejercicio
        botonEditar.setOnAction(this::EditarAction);
        //Agregar un nuevo ejercicio.
        botonAgregar.setOnAction(this::AgregarAction);

        // Eliminar Ejercicio
        botonEliminar.setOnAction(this::DeleteAction);
        //botonAplicar.setOnAction(this::buscarEjercicio);

        stage.show();
        LOGGER.info("Administracion Ejercicios iniciado");

    }

    private void EditarAction(ActionEvent action) {
        // Obtiene la fila seleccionada
        Ejercicio selectedEjercicio = (Ejercicio) tablaEjercicios.getSelectionModel().getSelectedItem();
        if (selectedEjercicio != null) {
            //Activar el modo edicion de la fila
            tablaEjercicios.edit(tablaEjercicios.getSelectionModel().getSelectedIndex(), columnaNombre);
        } else {
            // Muestra un mensaje si no hay fila seleccionada
            Alert alert = new Alert(Alert.AlertType.WARNING, "Seleccione un ejercicio para editar.");
            alert.show();
        }
    }

    private void AgregarAction(ActionEvent action) {
        try {
            Ejercicio ejercicio = new Ejercicio((Admin) usuario);
            EjercicioFactory.getModelo().crearEjercicio(ejercicio);
            informacionEjercicios = FXCollections.observableArrayList(EjercicioFactory.getModelo().findAll(new GenericType<List<Ejercicio>>() {
            }));
            tablaEjercicios.setItems(informacionEjercicios);
            tablaEjercicios.refresh();
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaEjercicios.refresh();
        }
    }

    private void DeleteAction(ActionEvent action) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Estas seguro de que quieres eliminar este ejercicio?");
        a.showAndWait();
        try {
            if (a.getResult().equals(ButtonType.CANCEL)) {
                action.consume();
            } else {
                //índice del elemento seleccionado en la tabla
                int selectedIndex = tablaEjercicios.getSelectionModel().getSelectedIndex();
                LOGGER.info(String.valueOf(selectedIndex));
                // Eliminar el ejercicio de la base de datos
                EjercicioFactory.getModelo().eliminarEjercicio(((Ejercicio) tablaEjercicios.getSelectionModel().getSelectedItem()).getId());

                // Ejercicio el  de la lista informacionIngredientes
                informacionEjercicios.remove(selectedIndex);

                tablaEjercicios.refresh();
            }
        } catch (Exception e) {
            if (e.getMessage() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "!Primero tienes que seleccionar un ejercicio!");
                alert.show();
            }
            String msg = "Error eliminando el ejercicio: " + e.getMessage();
            Alert alert = new Alert(Alert.AlertType.ERROR, msg);
            alert.show();
            LOGGER.log(Level.SEVERE, msg);
        }
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

    private void handleExitAction(WindowEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit? This will close the app.");
        a.showAndWait();
        try {
            if (a.getResult().equals(ButtonType.CANCEL)) {
                event.consume();
            } else {
                Platform.exit();
            }
        } catch (Exception e) {
            String msg = "Error closing the app: " + e.getMessage();
            Alert alert = new Alert(Alert.AlertType.ERROR, msg);
            alert.show();
            LOGGER.log(Level.SEVERE, msg);
        }
    }
}
