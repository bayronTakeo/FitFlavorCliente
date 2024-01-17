/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussinesLogic.ClienteFactory;
import exceptions.BusinessLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javax.ws.rs.core.GenericType;
import objects.Cliente;

/**
 *
 * @author bayro
 */
public class administradorClientesController {

    private Stage stage;

    private static final Logger LOGGER = Logger.getLogger("administradorClientesController.class");
    @FXML
    private ContextMenu menuTabla;
    @FXML
    private TableView tablaUsuarios;
    @FXML
    private TableColumn<Cliente, String> columnaEmail;
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

    private ObservableList<Cliente> informacionClientes;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle("Administracion clientes");
        stage.setResizable(false);
        stage.setOnCloseRequest(this::handleExitAction);

        tablaUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaEmail.setCellValueFactory(new PropertyValueFactory("email"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
        columnafecha.setCellValueFactory(new PropertyValueFactory("fechaNacimiento"));
        columnafecha.setCellFactory(column -> {
            TableCell<Cliente, Date> cell = new TableCell<Cliente, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(format.format(item));
                    }
                }
            };
            return cell;
        });
        columnaTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        columnaCodPostal.setCellValueFactory(new PropertyValueFactory("codigoPostal"));
        columnaContrania.setCellValueFactory(new PropertyValueFactory("contrasenia"));

        stage.show();
        try {

            informacionClientes = FXCollections.observableArrayList(ClienteFactory.getModelo().findAll(new GenericType<List<Cliente>>() {
            }));
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "La informacion no ha podido ser cargada.");
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaUsuarios.refresh();
        }
        tablaUsuarios.setItems(informacionClientes);
        tablaUsuarios.setEditable(true);

        //Editar columna nombreCompleto
        columnaNombre.setCellFactory(TextFieldTableCell.<Cliente>forTableColumn());
        columnaNombre.setOnEditCommit(
                (TableColumn.CellEditEvent<Cliente, String> t) -> {
                    try {
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNombreCompleto(t.getNewValue());
                        ClienteFactory.getModelo().actualizarCliente((Cliente) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setNombreCompleto(t.getOldValue());
                        tablaUsuarios.refresh();
                    }

                }
        );
        //Editar columna Email
        columnaEmail.setCellFactory(TextFieldTableCell.<Cliente>forTableColumn());
        columnaEmail.setOnEditCommit(
                (TableColumn.CellEditEvent<Cliente, String> t) -> {
                    try {
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setEmail(t.getNewValue());
                        ClienteFactory.getModelo().actualizarCliente((Cliente) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setEmail(t.getOldValue());
                        tablaUsuarios.refresh();
                    }

                }
        );
        //Editar columna telefono

        columnaTelefono.setCellFactory(TextFieldTableCell.<Cliente>forTableColumn());
        columnaTelefono.setOnEditCommit(
                (TableColumn.CellEditEvent<Cliente, String> t) -> {
                    try {
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTelefono(t.getNewValue());
                        ClienteFactory.getModelo().actualizarCliente((Cliente) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setEmail(t.getOldValue());
                        tablaUsuarios.refresh();
                    }
                }
        );
        //Editar columna direccion
        columnaDireccion.setCellFactory(TextFieldTableCell.<Cliente>forTableColumn());
        columnaDireccion.setOnEditCommit(
                (TableColumn.CellEditEvent<Cliente, String> t) -> {
                    try {
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setDireccion(t.getNewValue());
                        ClienteFactory.getModelo().actualizarCliente((Cliente) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setEmail(t.getOldValue());
                        tablaUsuarios.refresh();
                    }
                }
        );

        //Editar columna
        columnaCodPostal.setCellFactory(TextFieldTableCell.<Cliente>forTableColumn());
        columnaCodPostal.setOnEditCommit(
                (TableColumn.CellEditEvent<Cliente, String> t) -> {
                    try {
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setCodigoPostal(t.getNewValue());
                        ClienteFactory.getModelo().actualizarCliente((Cliente) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                        alert.show();
                        LOGGER.log(Level.SEVERE, ex.getMessage());
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setEmail(t.getOldValue());
                        tablaUsuarios.refresh();
                    }
                }
        );
        columnafecha.setCellFactory(param -> new DatePickerTable());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        columnafecha.setOnEditCommit((TableColumn.CellEditEvent<Cliente, Date> t) -> {
            try {

                ((Cliente) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setFechaNacimiento(dateFormatter.parse(dateFormatter.format(t.getNewValue())));
                ClienteFactory.getModelo().actualizarCliente((Cliente) t.getTableView().getSelectionModel().getSelectedItem());
            } catch (BusinessLogicException | ParseException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
                LOGGER.log(Level.SEVERE, ex.getMessage());
                ((Cliente) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setFechaNacimiento(t.getOldValue());
                tablaUsuarios.refresh();
            }
        });
        //Editar columna fechaNacimiento
        // Eliminar usuario
        menuTabla.getItems()
                .get(0).setOnAction(this::handleDeleteAction);
        LOGGER.info("AdministradorClientes iniciado");
    }

    private void handleDeleteAction(ActionEvent action) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Estas seguro de que quieres eliminar este usuario?");
        a.showAndWait();
        try {
            if (a.getResult().equals(ButtonType.CANCEL)) {
                action.consume();
            } else {
                ClienteFactory.getModelo().eliminarCliente(((Cliente) tablaUsuarios.getSelectionModel().getSelectedItem()).getUser_id());
                tablaUsuarios.getItems().remove(tablaUsuarios.getSelectionModel().getSelectedItem());
                tablaUsuarios.refresh();
            }
        } catch (Exception e) {
            String msg = "Error eliminando el usuario: " + e.getMessage();
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
