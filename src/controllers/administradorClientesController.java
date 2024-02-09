/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussinesLogic.AdminFactory;
import bussinesLogic.ClienteFactory;
import exceptions.BusinessLogicException;
import files.AsymmetricCliente;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.ws.rs.core.GenericType;
import javax.xml.bind.DatatypeConverter;
import objects.Admin;
import objects.Cliente;
import objects.EnumPrivilegios;
import objects.Ingrediente;
import objects.TipoIngrediente;
import objects.Usuario;

/**
 *
 * @author bayro
 */
public class administradorClientesController {

    private Stage stage;

    private Usuario cliente;
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
    @FXML
    private TableColumn<Cliente, EnumPrivilegios> columnaPermisos;

    @FXML
    private Button botonAgregar, botonEliminar, botonEditar, botonBuscar;
    @FXML
    private TextField textfieldBuscar;

    private ObservableList<Cliente> informacionClientes;

    private ObservableList<EnumPrivilegios> opciones
            = FXCollections.observableArrayList(EnumPrivilegios.values());

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        stage.setScene(scene);
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
        columnaPermisos.setCellValueFactory(new PropertyValueFactory("privilegio"));
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

        //Editar tipo de ingrediente
        columnaPermisos.setCellFactory(ComboBoxTableCell.<Cliente, EnumPrivilegios>forTableColumn(EnumPrivilegios.values()));
        columnaPermisos.setOnEditCommit(
                (TableColumn.CellEditEvent<Cliente, EnumPrivilegios> t) -> {

                    Cliente tipoSeleccionado = (Cliente) tablaUsuarios.getSelectionModel().getSelectedItem();
                    EnumPrivilegios valorOriginal = t.getOldValue();
                    ComboBox<EnumPrivilegios> comboBox = new ComboBox<>();
                    comboBox.setItems(opciones);
                    try {
                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setPrivilegio(t.getNewValue());
                        ClienteFactory.getModelo().actualizarCliente((Cliente) t.getTableView().getSelectionModel().getSelectedItem());
                    } catch (BusinessLogicException ex) {

                        ((Cliente) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setPrivilegio(valorOriginal);
                        tablaUsuarios.refresh();
                    }
                });
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
        //Editar columna fechaNacimiento
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
        //Buscarr un usuario
        botonBuscar.setOnAction(this::buscarCliente);
        //Editar un cliente
        botonEditar.setOnAction(this::EditarAction);
        //Agregar un nuevo usuario.
        botonAgregar.setOnAction(this::AgregarAction);

        // Eliminar usuario
        menuTabla.getItems()
                .get(0).setOnAction(this::DeleteAction);
        botonEliminar.setOnAction(this::DeleteAction);
        stage.show();
        LOGGER.info("AdministradorClientes iniciado");
    }

    private void buscarCliente(ActionEvent action) {
        LOGGER.info("Buscando clientes: ");
        try {
            if (!textfieldBuscar.getText().isEmpty()) {

                informacionClientes = FXCollections.observableArrayList(ClienteFactory.getModelo().buscarNombre(new GenericType<Cliente>() {
                }, textfieldBuscar.getText()));
                tablaUsuarios.setItems(informacionClientes);
                tablaUsuarios.refresh();
            } else {
                informacionClientes = FXCollections.observableArrayList(ClienteFactory.getModelo().findAll(new GenericType<List<Cliente>>() {
                }));
                tablaUsuarios.setItems(informacionClientes);
                tablaUsuarios.refresh();
            }
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaUsuarios.refresh();
        }
    }

    private void EditarAction(ActionEvent action) {
        // Obtiene la fila seleccionada
        Cliente selectedCliente = (Cliente) tablaUsuarios.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            //Activar el modo edicion de la fila
            tablaUsuarios.edit(tablaUsuarios.getSelectionModel().getSelectedIndex(), columnaEmail);
        } else {
            // Muestra un mensaje si no hay fila seleccionada
            Alert alert = new Alert(Alert.AlertType.WARNING, "Seleccione un cliente para editar.");
            alert.show();
        }
    }

    private void AgregarAction(ActionEvent action) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("Elija una opción:");
            alert.setContentText("¿Que quieres crear?");

            ButtonType buttonTypeOne = new ButtonType("Cliente");
            ButtonType buttonTypeTwo = new ButtonType("Admin");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            alert.showAndWait().ifPresent(buttonType -> {
                if (buttonType == buttonTypeOne) {

                    try {
                        Cliente client = new Cliente();
                        byte[] passwordBytes = new AsymmetricCliente().cipher("abcd*1234");
                        client.setContrasenia(DatatypeConverter.printHexBinary(passwordBytes));
                        ClienteFactory.getModelo().crearCliente(client);
                    } catch (BusinessLogicException ex) {
                        Logger.getLogger(administradorClientesController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (buttonType == buttonTypeTwo) {
                    try {
                        Admin admin = new Admin();
                        byte[] passwordBytes = new AsymmetricCliente().cipher("abcd*1234");
                        admin.setContrasenia(DatatypeConverter.printHexBinary(passwordBytes));
                        AdminFactory.getModelo().crearAdmin(admin);
                    } catch (BusinessLogicException ex) {
                        Logger.getLogger(administradorClientesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    alert.close();

                }
            });

            informacionClientes = FXCollections.observableArrayList(ClienteFactory.getModelo().findAll(new GenericType<List<Cliente>>() {
            }));
            tablaUsuarios.setItems(informacionClientes);
            tablaUsuarios.refresh();
        } catch (BusinessLogicException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaUsuarios.refresh();
        }
    }

    private void DeleteAction(ActionEvent action) {
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
