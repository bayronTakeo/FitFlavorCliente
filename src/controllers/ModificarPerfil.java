/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Encriptacion.Hash;
import bussinesLogic.ClienteFactory;
import exceptions.BusinessLogicException;
import files.AsymmetricCliente;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.xml.bind.DatatypeConverter;
import objects.Cliente;
import objects.EnumObjetivo;
import objects.EnumSexo;
import objects.TipoIngrediente;
import objects.Usuario;

/**
 *
 * @author bayro
 */
public class ModificarPerfil {

    private Stage stage;

    private static final Logger LOGGER = Logger.getLogger("ModificarPerfil.class");

    @FXML
    private TextField nombre, email, altura, peso;

    @FXML
    private ComboBox genero, objetivo;

    @FXML
    private DatePicker fecha;

    @FXML
    private Button botonCambiar;

    @FXML
    private Hyperlink cambiarContraseña;

    private Usuario cliente;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    private ObservableList<EnumSexo> opcionesG
            = FXCollections.observableArrayList(EnumSexo.values());

    private ObservableList<EnumObjetivo> opcionesO
            = FXCollections.observableArrayList(EnumObjetivo.values());

    private Cliente cli;

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Diario");
        stage.setResizable(false);

        cli = (Cliente) cliente;
        //Cargo la combo con las opciones de genero
        genero.setItems(opcionesG);

        //Cargo la combo con las opciones de objetivo
        objetivo.setItems(opcionesO);

        //Cargo el nombre del cliente
        nombre.setText(cliente.getNombreCompleto());

        //CArgo el email del cliente
        email.setText(cliente.getEmail());
        //CArgo la altura del cliente
        altura.setText(cli.getAltura());

        //Cargo elpeso del cliente
        peso.setText(Float.toString(cli.getPeso()));

        //Establezco el genero del cliente
        genero.setValue(cli.getSexo());

        //Establezo el objetivo del cliente
        objetivo.setValue(cli.getObjetivo());

        botonCambiar.setOnAction(this::cambiarCli);

        cambiarContraseña.setOnAction(this::cambiar);
        stage.show();
        LOGGER.info("Diario iniciado!");
    }

    private void cambiar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cambiar contraseña");
        alert.setHeaderText("Introduce tu nueva contraseña: ");

        GridPane grid = new GridPane();

        TextField contraseña = new TextField();
        contraseña.setPromptText("");

        grid.add(contraseña, 0, 0);

        alert.getDialogPane().setContent(grid);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    Cliente client = ClienteFactory.getModelo().buscarCliente(new GenericType<Cliente>() {
                    }, cliente.getEmail());
                    LOGGER.log(Level.INFO, "Cliente encontrado");
                    byte[] passwordBytes = new AsymmetricCliente().cipher(contraseña.getText());
                    cliente.setContrasenia(DatatypeConverter.printHexBinary(passwordBytes));
                    ClienteFactory.getModelo().actualizarContraseña(client);

                    // Muestra un segundo Alert indicando que el correo ha sido enviado con éxito
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Éxito!");
                    successAlert.setHeaderText("Correo enviado");
                    successAlert.setContentText("Se ha actualizado tu contraseña correctamente!.");
                    successAlert.showAndWait();

                } catch (BusinessLogicException ex) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    alerta.show();
                    LOGGER.log(Level.SEVERE, ex.getMessage());
                }
            }
        });
    }

    public void cambiarCli(ActionEvent event) {
        // Verificar que ningún TextField esté vacío
        if (nombre.getText().isEmpty() || email.getText().isEmpty() || altura.getText().isEmpty() || peso.getText().isEmpty()) {
            // Mostrar una alerta indicando campos vacíos
            mostrarAlerta("Campos Vacíos", "Por favor, complete todos los campos antes de realizar el cambio.");
            return; // No continuar con la actualización si hay campos vacíos
        }

        try {
            // Establecer los nuevos datos en el objeto cliente
            cli.setNombreCompleto(nombre.getText());
            cli.setEmail(email.getText());
            cli.setAltura(altura.getText());
            cli.setPeso(Float.parseFloat(peso.getText()));
            cli.setSexo((EnumSexo) genero.getValue());
            cli.setObjetivo((EnumObjetivo) objetivo.getValue());
            Instant instant = fecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant();
            cli.setFechaNacimiento(Date.from(instant));

            ClienteFactory.getModelo().actualizarCliente(cli);

            // Mostrar una alerta indicando que la actualización fue exitosa
            mostrarAlerta("Actualización Exitosa", "El perfil ha sido actualizado correctamente.");

            LOGGER.info("Cliente actualizado exitosamente.");
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ModificarPerfil.class.getName()).log(Level.SEVERE, null, ex);
            // Manejar la excepción según tus necesidades
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
