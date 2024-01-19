/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussinesLogic.IngredienteFactory;
import exceptions.BusinessLogicException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.ws.rs.core.GenericType;
import logicaTablas.floatFormateador;
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
    private Button botonFiltros, botonCancelar, botonAgregar, botonEliminar, botonEditar, botonAplicar;
    @FXML
    private Slider sliderKcal, sliderPrecio, sliderCarb, sliderProte, sliderGrasas;
    @FXML
    private ComboBox<TipoIngrediente> comboTipo;
    @FXML
    private TableView tablaIngredientes;
    @FXML
    private TableColumn<Ingrediente, TipoIngrediente> columnaTipo;
    @FXML
    private TableColumn<Ingrediente, String> columnaNombre;
    @FXML
    private TableColumn<Ingrediente, Float> columnaPrecio;
    @FXML
    private TableColumn<Ingrediente, Float> columnaKcal;
    @FXML
    private TableColumn<Ingrediente, Float> columnaCarb;
    @FXML
    private TableColumn<Ingrediente, Float> columnaProteinas;
    @FXML
    private TableColumn<Ingrediente, Float> columnaGrasas;

    private ObservableList<Ingrediente> informacionIngredientes;

    private ObservableList<TipoIngrediente> opciones
            = FXCollections.observableArrayList(TipoIngrediente.values());

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
        comboTipo.setItems(opciones);
        tablaIngredientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory<>("tipoIngrediente"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaKcal.setCellValueFactory(new PropertyValueFactory<>("kcal"));
        columnaCarb.setCellValueFactory(new PropertyValueFactory<>("carbohidratos"));
        columnaProteinas.setCellValueFactory(new PropertyValueFactory<>("proteinas"));
        columnaGrasas.setCellValueFactory(new PropertyValueFactory<>("grasas"));

        try {

            informacionIngredientes = FXCollections.observableArrayList(IngredienteFactory.getModelo().findAll(new GenericType<List<Ingrediente>>() {
            }));
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La informacion no ha podido ser cargada.");
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaIngredientes.refresh();

        }
        for (Ingrediente i : informacionIngredientes) {
            LOGGER.info(i.toString());
        }
        tablaIngredientes.setItems(informacionIngredientes);
        tablaIngredientes.setEditable(true);

        //Editar columna nombre
        columnaNombre.setCellFactory(TextFieldTableCell.<Ingrediente>forTableColumn());
        columnaNombre.setOnEditCommit(
                (TableColumn.CellEditEvent<Ingrediente, String> t) -> {
                    try {
                        ((Ingrediente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNombre(t.getNewValue());
                        IngredienteFactory.getModelo().updateIngrediente((Ingrediente) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Ingrediente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNombre(t.getOldValue());
                        tablaIngredientes.refresh();
                    }

                }
        );
        //Editar tipo de ingrediente
        columnaTipo.setCellFactory(ComboBoxTableCell.<Ingrediente, TipoIngrediente>forTableColumn(TipoIngrediente.values()));
        columnaTipo.setOnEditCommit(
                (CellEditEvent<Ingrediente, TipoIngrediente> t) -> {

                    Ingrediente tipoSeleccionado = (Ingrediente) tablaIngredientes.getSelectionModel().getSelectedItem();
                    TipoIngrediente valorOriginal = t.getOldValue();
                    ComboBox<TipoIngrediente> comboBox = new ComboBox<>();
                    comboBox.setItems(opciones);
                    try {
                        ((Ingrediente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTipoIngrediente(t.getNewValue());
                        IngredienteFactory.getModelo().updateIngrediente((Ingrediente) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {

                        ((Ingrediente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTipoIngrediente(valorOriginal);
                        tablaIngredientes.refresh();
                    }
                });
        //Editar columna precio
        columnaPrecio.setCellFactory(TextFieldTableCell.<Ingrediente, Float>forTableColumn(new floatFormateador()));
        columnaPrecio.setOnEditCommit((CellEditEvent<Ingrediente, Float> t) -> {
            Ingrediente seleccionado = (Ingrediente) tablaIngredientes.getSelectionModel().getSelectedItem();
            Float precio = seleccionado.getPrecio();
            try {
                if (t.getNewValue() <= 9999) {
                    ((Ingrediente) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPrecio(t.getNewValue());
                    IngredienteFactory.getModelo().updateIngrediente((Ingrediente) t.getTableView().getSelectionModel().getSelectedItem());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "El número maximo posible es de 4 digitos!");
                    alert.show();
                    informacionIngredientes = FXCollections.observableArrayList(IngredienteFactory.getModelo().findAll(new GenericType<List<Ingrediente>>() {
                    }));
                    tablaIngredientes.setItems(informacionIngredientes);
                }
            } catch (BusinessLogicException ex) {
                ((Ingrediente) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setPrecio(precio);
                tablaIngredientes.refresh();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error en el servidor" + ex.getMessage());
                alert.show();
                LOGGER.log(Level.SEVERE, "Error al intentar actualizar", ex.getMessage());
            } catch (NullPointerException ex) {
                ((Ingrediente) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setPrecio(t.getOldValue());
                tablaIngredientes.refresh();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Este campo solo admite números!");
                alert.show();
                LOGGER.log(Level.SEVERE, "Error al intentar actualizar", ex.getMessage());
            }
        });
        botonEliminar.setOnAction(this::DeleteAction);

        botonAgregar.setOnAction(this::AgregarAction);

        botonAplicar.setOnAction(this::buscarCliente);
        stage.show();
        LOGGER.info("Pagina principal iniciada");
    }

    private void buscarCliente(ActionEvent action) {
        LOGGER.info("Buscando ingredientes: ");
        try {

            informacionIngredientes = FXCollections.observableArrayList(
                    IngredienteFactory.getModelo().buscarFiltros(
                            new GenericType<List<Ingrediente>>() {
                    },
                            comboTipo.getValue() != null ? comboTipo.getValue() : null,
                            null,
                            (float) sliderPrecio.getValue() != 0 ? (float) sliderPrecio.getValue() : null,
                            (float) sliderKcal.getValue() != 0 ? (float) sliderKcal.getValue() : null,
                            (float) sliderCarb.getValue() != 0 ? (float) sliderCarb.getValue() : null,
                            (float) sliderProte.getValue() != 0 ? (float) sliderProte.getValue() : null,
                            (float) sliderGrasas.getValue() != 0 ? (float) sliderGrasas.getValue() : null
                    )
            );
            cerrarMenuFiltros(action);
            tablaIngredientes.setItems(informacionIngredientes);
            tablaIngredientes.refresh();

        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaIngredientes.refresh();
        }
    }

    private void AgregarAction(ActionEvent action) {
        try {
            Ingrediente ingrediente = new Ingrediente();
            IngredienteFactory.getModelo().crearIngrediente(ingrediente);
            informacionIngredientes = FXCollections.observableArrayList(IngredienteFactory.getModelo().findAll(new GenericType<List<Ingrediente>>() {
            }));
            tablaIngredientes.setItems(informacionIngredientes);
            tablaIngredientes.refresh();
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaIngredientes.refresh();
        }
    }

    private void DeleteAction(ActionEvent action) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Estas seguro de que quieres eliminar este ingrediente?");
        a.showAndWait();
        try {
            if (a.getResult().equals(ButtonType.CANCEL)) {
                action.consume();
            } else {
                //índice del elemento seleccionado en la tabla
                int selectedIndex = tablaIngredientes.getSelectionModel().getSelectedIndex();

                // Eliminar el ingrediente de la base de datos
                IngredienteFactory.getModelo().deleteIngrediente(((Ingrediente) tablaIngredientes.getSelectionModel().getSelectedItem()).getId());

                // Eliminar el ingrediente de la lista informacionIngredientes
                informacionIngredientes.remove(selectedIndex);

                tablaIngredientes.refresh();
            }
        } catch (Exception e) {
            if (e.getMessage() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "!Primero tienes que seleccionar un cliente!");
                alert.show();
            }
            String msg = "Error eliminando el ingrediente: " + e.getMessage();
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
}
