package controllers;

import controllers.administradorClientesController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuLateralControlller {

    private Stage stage;
    private static final Logger LOGGER = Logger.getLogger("MenuLateralControlller.class");

    @FXML
    private Button botonPrincipal, botonEjercicio, botonRecetas, botonPerfil, botonIngredientes;

    private PaginaPrincipalController paginaPrincipalController;

    public void init(PaginaPrincipalController paginaPrincipalController) {
        this.paginaPrincipalController = paginaPrincipalController;
    }

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
        LOGGER.info("ALgo hace");
        botonPerfil.setOnAction(
                this::handleButtonPerfilAction);
        stage.show();
    }

    // Elimina el método initStage
    @FXML
    private void handleButtonPerfilAction(ActionEvent event) {
        try {
            LOGGER.info("Entrando");
            // Cambiar la lógica según lo que quieras hacer con el botón "Perfil"
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/administradorClientes.fxml"));
            Parent root = (Parent) loader.load();

            administradorClientesController controller = ((administradorClientesController) loader.getController());

            controller.setStage(stage);

            controller.initStage(root);
        } catch (IOException | IllegalStateException ex) {
            // Si hay un error al intentar cambiar la vista, muestra una alerta.
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
            LOGGER.log(Level.SEVERE, "Error al intentar abrir la ventana de clientes: {0}", ex.getMessage());
        }
    }
}
