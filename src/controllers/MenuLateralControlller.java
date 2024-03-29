package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import objects.Cliente;
import objects.Usuario;

public class MenuLateralControlller {

    private Stage stage = new Stage();
    private SesionCliente sesionCliente = SesionCliente.getInstance();

    private Usuario cliente2 = sesionCliente.getCliente();
    private static final Logger LOGGER = Logger.getLogger("MenuLateralControlller.class");

    @FXML
    private Button botonPrincipal, botonEjercicio, botonRecetas, botonPerfil, botonIngredientes, botonDiario;
    @FXML
    private Pane menuLateral;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void cerrarSesion(ActionEvent event) {
        try {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Estas seguro de cerrar sesion?");
            a.showAndWait();
            try {
                if (a.getResult().equals(ButtonType.CANCEL)) {
                    event.consume();
                } else {
                    LOGGER.info("Entrando");
                    ((Stage) this.menuLateral.getScene().getWindow()).close();
                    // Cambiar la lógica según lo que quieras hacer con el botón "Perfil"
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/SignIn.fxml"));
                    Parent root = (Parent) loader.load();

                    SignInController controller = ((SignInController) loader.getController());
                    //controller.setUsuario(cliente2);
                    controller.setStage(stage);

                    controller.initStage(root);
                }
            } catch (Exception e) {
                String msg = "Error closing the app: " + e.getMessage();
                Alert alert = new Alert(Alert.AlertType.ERROR, msg);
                alert.show();
                LOGGER.log(Level.SEVERE, msg);
            }

        } catch (IllegalStateException ex) {
            // Si hay un error al intentar cambiar la vista, muestra una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, "Error al intentar abrir la ventana de clientes: {0}", ex.getMessage());
        }
    }

    @FXML
    public void abrirRecetas(ActionEvent event) {
        try {
            LOGGER.info("Entrando");
            ((Stage) this.menuLateral.getScene().getWindow()).close();
            // Cambiar la lógica según lo que quieras hacer con el botón "Perfil"
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Recetas.fxml"));
            Parent root = (Parent) loader.load();

            RecetaController controller = ((RecetaController) loader.getController());
            controller.setUsuario(cliente2);
            controller.setStage(stage);

            controller.initStage(root);
        } catch (IOException | IllegalStateException ex) {
            // Si hay un error al intentar cambiar la vista, muestra una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, "Error al intentar abrir la ventana de clientes: {0}", ex.getMessage());
        }
    }

    @FXML
    public void abrirDiario(ActionEvent event) {
        try {
            LOGGER.info("Entrando");
            ((Stage) this.menuLateral.getScene().getWindow()).close();
            // Cambiar la lógica según lo que quieras hacer con el botón "Perfil"
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/Diario.fxml"));
            Parent root = (Parent) loader.load();

            DiarioController controller = ((DiarioController) loader.getController());
            controller.setUsuario(cliente2);
            controller.setStage(stage);

            controller.initStage(root);
        } catch (IOException | IllegalStateException ex) {
            // Si hay un error al intentar cambiar la vista, muestra una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, "Error al intentar abrir la ventana de clientes: {0}", ex.getMessage());
        }
    }

    @FXML
    public void abrirEjercicios(ActionEvent event) {
        try {
            LOGGER.info("Entrando");
            ((Stage) this.menuLateral.getScene().getWindow()).close();
            // Cambiar la lógica según lo que quieras hacer con el botón "Perfil"
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/EjercicioAdmin.fxml"));
            Parent root = (Parent) loader.load();

            EjercicioAdminController controller = ((EjercicioAdminController) loader.getController());
            controller.setUsuario(cliente2);
            controller.setStage(stage);

            controller.initStage(root);
        } catch (IOException | IllegalStateException ex) {
            // Si hay un error al intentar cambiar la vista, muestra una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, "Error al intentar abrir la ventana de clientes: {0}", ex.getMessage());
        }
    }

    // Elimina el método initStage
    @FXML
    public void handleButtonClientsAction(ActionEvent event) {
        try {
            if (cliente2 instanceof Cliente) {
                LOGGER.info("Entrando");
                ((Stage) this.menuLateral.getScene().getWindow()).close();
                // Cambiar la lógica según lo que quieras hacer con el botón "Perfil"
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ModificarPerfil.fxml"));
                Parent root = (Parent) loader.load();

                ModificarPerfil controller = ((ModificarPerfil) loader.getController());
                controller.setCliente(cliente2);
                controller.setStage(stage);

                controller.initStage(root);
            } else {
                LOGGER.info("Entrando");
                ((Stage) this.menuLateral.getScene().getWindow()).close();
                // Cambiar la lógica según lo que quieras hacer con el botón "Perfil"
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/administradorClientes.fxml"));
                Parent root = (Parent) loader.load();

                administradorClientesController controller = ((administradorClientesController) loader.getController());
                controller.setCliente(cliente2);
                controller.setStage(stage);

                controller.initStage(root);
            }

        } catch (IOException | IllegalStateException ex) {
            // Si hay un error al intentar cambiar la vista, muestra una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, "Error al intentar abrir la ventana de clientes: {0}", ex.getMessage());
        }
    }

    @FXML
    public void abrirIngredientes(ActionEvent event) {
        try {
            LOGGER.info("Llega bien a ingreeeeee" + cliente2.getEmail());
            LOGGER.info("Entrando");
            ((Stage) this.menuLateral.getScene().getWindow()).close();

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/IngredientesAdmin.fxml"));
            Parent root = (Parent) loader.load();

            IngredientesAdminController controller = ((IngredientesAdminController) loader.getController());

            // Elimina estas líneas de código:
            // controller.setStage(stage);
            // controller.setCliente(cliente);
            // Llama a initStage después de configurar el cliente
            controller.setStage(stage);
            controller.setCliente(cliente2);
            controller.initStage(root);
        } catch (IOException | IllegalStateException ex) {
            // Si hay un error al intentar cambiar la vista, muestra una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, "Error al intentar abrir la ventana de clientes: {0}", ex.getMessage());
        }
    }

}
