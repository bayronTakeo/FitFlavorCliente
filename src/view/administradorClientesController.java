/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bussinesLogic.ClienteFactory;
import exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    private TableColumn<Cliente, String> columnafecha;
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
        LOGGER.info("SingIn window initialized");

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
