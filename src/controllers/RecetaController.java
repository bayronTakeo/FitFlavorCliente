/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussinesLogic.RecetaFactory;
import exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.ws.rs.core.GenericType;
import logicaTablas.floatFormateador;
import objects.Ingrediente;
import objects.Receta;
import objects.TipoReceta;
import objects.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author paula
 */
public class RecetaController {

    private Stage stage;

    private static final Logger LOGGER = Logger.getLogger("RecetaController.class");
    @FXML
    private ContextMenu menuTabla;
    @FXML
    private TableView tablaRecetas;
    @FXML
    private TableColumn<Receta, TipoReceta> columnaTipo;
    @FXML
    private TableColumn<Receta, String> columnaNombre;
    @FXML
    private TableColumn<Receta, Float> columnaDuracion;
    @FXML
    private TableColumn<Receta, List> columnaIngrediente;
    @FXML
    private TableColumn<Receta, String> columnaPasos;
    @FXML
    private TableColumn<Receta, Float> columnaPrecio;
    @FXML
    private TableColumn<Receta, Boolean> columnaVegetariano;
    @FXML
    private TableColumn<Receta, Boolean> columnaVegano;
    @FXML
    private Button botonAgregar, botonEliminar, botonEditar, botonInforme;

    private ObservableList<Receta> informacionRecetas;

    private List<Ingrediente> ingredientes;

    private Usuario cliente;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsuario(Usuario cliente) {
        this.cliente = cliente;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        // Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Recetas");
        stage.setResizable(false);
        stage.setOnCloseRequest(this::handleExitAction);

        tablaRecetas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaTipo.setCellValueFactory(new PropertyValueFactory("tipoReceta"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));

//         columnaIngrediente.setCellValueFactory(new PropertyValueFactory("ingredientes"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        columnaVegetariano.setCellValueFactory(new PropertyValueFactory("esVegetariano"));
        columnaVegano.setCellValueFactory(new PropertyValueFactory("esVegano"));
        columnaPasos.setCellValueFactory(new PropertyValueFactory("pasos"));

        stage.show();

        try {

            informacionRecetas = FXCollections.observableArrayList(RecetaFactory.getModelo().listaRecetas(new GenericType<List<Receta>>() {
            }));
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La informacion no ha podido ser cargada.");
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaRecetas.refresh();
        }
//        for (Receta receta : informacionRecetas) {
//            LOGGER.info("Entra al for");
//            if (receta.getIngredientes().size() != 0) {
//                LOGGER.info("Entra al if");
//                ingredientes = receta.getIngredientes();
//            }
//        }
//        for (Ingrediente ing : ingredientes) {
//            LOGGER.info(ing.toString());
//        }
        tablaRecetas.setItems(informacionRecetas);
        tablaRecetas.setEditable(true);

        //Editar columna nombre
        columnaNombre.setCellFactory(TextFieldTableCell.<Receta>forTableColumn());
        columnaNombre.setOnEditCommit(
                (TableColumn.CellEditEvent<Receta, String> t) -> {
                    try {
                        ((Receta) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNombre(t.getNewValue());
                        RecetaFactory.getModelo().updateReceta((Receta) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Receta) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNombre(t.getOldValue());
                        tablaRecetas.refresh();
                    }
                }
        );

        //Editar columna pasos
        columnaPasos.setCellFactory(TextFieldTableCell.<Receta>forTableColumn());
        columnaPasos.setOnEditCommit(
                (TableColumn.CellEditEvent<Receta, String> t) -> {
                    try {
                        ((Receta) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setPasos(t.getNewValue());
                        RecetaFactory.getModelo().updateReceta((Receta) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Receta) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setPasos(t.getOldValue());
                        tablaRecetas.refresh();
                    }
                }
        );

        //Editar columna duracion
        columnaDuracion.setCellFactory(TextFieldTableCell.<Receta, Float>forTableColumn(new floatFormateador()));
        columnaDuracion.setOnEditCommit((CellEditEvent<Receta, Float> t) -> {
            Receta seleccionado = (Receta) tablaRecetas.getSelectionModel().getSelectedItem();
            Float duracion = seleccionado.getDuracion();
            try {
                if (t.getNewValue() <= 9999) {
                    ((Receta) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setDuracion(t.getNewValue());
                    RecetaFactory.getModelo().updateReceta((Receta) t.getTableView().getSelectionModel().getSelectedItem());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "El número maximo posible es de 4 digitos!");
                    alert.show();
                    informacionRecetas = FXCollections.observableArrayList(RecetaFactory.getModelo().listaRecetas(new GenericType<List<Receta>>() {
                    }));
                    tablaRecetas.setItems(informacionRecetas);
                }
            } catch (BusinessLogicException ex) {
                ((Receta) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setDuracion(duracion);
                tablaRecetas.refresh();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error en el servidor" + ex.getMessage());
                alert.show();
                LOGGER.log(Level.SEVERE, "Error al intentar actualizar", ex.getMessage());
            } catch (NullPointerException ex) {
                ((Receta) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setDuracion(t.getOldValue());
                tablaRecetas.refresh();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Este campo solo admite números!");
                alert.show();
                LOGGER.log(Level.SEVERE, "Error al intentar actualizar", ex.getMessage());
            }
        });

        //Editar columna precio
        columnaPrecio.setCellFactory(TextFieldTableCell.<Receta, Float>forTableColumn(new floatFormateador()));
        columnaPrecio.setOnEditCommit((CellEditEvent<Receta, Float> t) -> {
            Receta seleccionado = (Receta) tablaRecetas.getSelectionModel().getSelectedItem();
            Float precio = seleccionado.getPrecio();
            try {
                if (t.getNewValue() <= 9999) {
                    ((Receta) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPrecio(t.getNewValue());
                    RecetaFactory.getModelo().updateReceta((Receta) t.getTableView().getSelectionModel().getSelectedItem());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "El número maximo posible es de 4 digitos!");
                    alert.show();
                    informacionRecetas = FXCollections.observableArrayList(RecetaFactory.getModelo().listaRecetas(new GenericType<List<Receta>>() {
                    }));
                    tablaRecetas.setItems(informacionRecetas);
                }
            } catch (BusinessLogicException ex) {
                ((Receta) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setPrecio(precio);
                tablaRecetas.refresh();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error en el servidor" + ex.getMessage());
                alert.show();
                LOGGER.log(Level.SEVERE, "Error al intentar actualizar", ex.getMessage());
            } catch (NullPointerException ex) {
                ((Receta) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setPrecio(t.getOldValue());
                tablaRecetas.refresh();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Este campo solo admite números!");
                alert.show();
                LOGGER.log(Level.SEVERE, "Error al intentar actualizar", ex.getMessage());
            }
        });

        botonEditar.setOnAction(this::EditarAction);
        //Agregar una nueva receta.
        botonAgregar.setOnAction(this::AgregarAction);

        // Eliminar receta
//        menuTabla.getItems()
//                .get(0).setOnAction(this::DeleteAction);
        botonEliminar.setOnAction(this::DeleteAction);
        stage.show();
        LOGGER.info("Recetas iniciado");
        //botonInforme.setOnAction(this::InformeAction);
    }

    @FXML
    private void InformeAction(ActionEvent event) {
        try {
            LOGGER.info("Beginning printing action...");
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/RecetaControllerReport.jrxml"));

            JRBeanCollectionDataSource dataItems = new JRBeanCollectionDataSource((Collection<Receta>) this.tablaRecetas.getItems());

            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);

            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
        } catch (JRException ex) {

            LOGGER.log(Level.SEVERE,
                    "RecetaController: Error printing report: {0}",
                    ex.getMessage());
        }
    }

    private void EditarAction(ActionEvent action) {
        // Obtiene la fila seleccionada
        Receta selectedReceta = (Receta) tablaRecetas.getSelectionModel().getSelectedItem();
        if (selectedReceta != null) {
            //Activar el modo edicion de la fila
            tablaRecetas.edit(tablaRecetas.getSelectionModel().getSelectedIndex(), columnaNombre);
        } else {
            // Muestra un mensaje si no hay fila seleccionada
            Alert alert = new Alert(Alert.AlertType.WARNING, "Seleccione una receta para editar.");
            alert.show();
        }
    }

    private void AgregarAction(ActionEvent action) {
        try {
            Receta re = new Receta();
            RecetaFactory.getModelo().createReceta(re);
            informacionRecetas = FXCollections.observableArrayList(RecetaFactory.getModelo().listaRecetas(new GenericType<List<Receta>>() {
            }));
            tablaRecetas.setItems(informacionRecetas);
            tablaRecetas.refresh();
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaRecetas.refresh();
        }
    }

    private void DeleteAction(ActionEvent action) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Estas seguro de que quieres eliminar esta receta");
        a.showAndWait();
        try {
            if (a.getResult().equals(ButtonType.CANCEL)) {
                action.consume();
            } else {
                RecetaFactory.getModelo().deleteReceta(((Receta) tablaRecetas.getSelectionModel().getSelectedItem()).getId());
                tablaRecetas.getItems().remove(tablaRecetas.getSelectionModel().getSelectedItem());
                tablaRecetas.refresh();
            }
        } catch (Exception e) {
            if (e.getMessage() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "!Primero tienes que seleccionar una receta!");
                alert.show();
            }
            String msg = "Error eliminando la receta: " + e.getMessage();
            Alert alert = new Alert(Alert.AlertType.ERROR, msg);
            alert.show();
            LOGGER.log(Level.SEVERE, msg);
        }
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
