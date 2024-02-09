package cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import bussinesLogic.RecetaFactory;
import bussinesLogic.RecetaInterfaz;
import bussinesLogic.UsuarioFactory;
import bussinesLogic.UsuarioInterfaz;
import controllers.RecetaController;
import exceptions.BusinessLogicException;
import files.AsymmetricCliente;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.xml.bind.DatatypeConverter;
import objects.Cliente;
import objects.Receta;
import objects.TipoReceta;
import objects.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author paula
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecetaControllerTest extends ApplicationTest {

    private ObservableList<Receta> ingrediente;
    private Usuario usuario;

    private RecetaInterfaz ri = RecetaFactory.getModelo();

    private UsuarioInterfaz usuIn = UsuarioFactory.getModelo();
    private Button botonAgregar, botonEliminar, botonEditar;
    private TableView table;

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        byte[] passwordBytes = new AsymmetricCliente().cipher("abcd*1234");
        usuario = usuIn.signIn(new GenericType<Cliente>() {
        }, "usuario3@gmail.com", DatatypeConverter.printHexBinary(passwordBytes));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Recetas.fxml"));
        System.out.println(loader.getLocation());
        Parent root = loader.load();
        RecetaController administrador = loader.getController();
        administrador.setStage(stage);

        administrador.initStage(root);

        this.stage = stage;

        botonAgregar = lookup("#botonAgregar").query();
        botonEliminar = lookup("#botonEliminar").query();
        botonEditar = lookup("#botonEditar").query();

        table = lookup("#tablaRecetas").queryTableView();
    }

    @Test
    // @Ignore
    public void test1_initStage() {
        verifyThat(botonAgregar, isVisible());
        verifyThat(botonAgregar, isEnabled());
        verifyThat(botonEliminar, isVisible());
        verifyThat(botonEliminar, isEnabled());
        verifyThat(botonEditar, isVisible());
        verifyThat(botonEditar, isEnabled());
    }

    @Test
    // @Ignore
    public void createReceta() {
        try {
            int rowCount = table.getItems().size();

            clickOn(botonAgregar);
            System.out.println(table.getItems().size());
            assertEquals("La fila no se ha creado!!!", rowCount + 1, table.getItems().size());
            List<Receta> recetas = ri.listaRecetas(new GenericType<List<Receta>>() {
            });

            Receta recetaServer = recetas.get(recetas.size() - 1);
            List<Receta> recetaTabla = table.getItems();

            assertEquals("El recurrente no se ha añdido!!!",
                    recetaTabla.stream().filter(c -> c.getId().equals(recetaServer.getId())).count(), 1);

        } catch (BusinessLogicException ex) {
            Logger.getLogger(ClienteController_Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    //@Ignore
    public void test3_handleModifyRecurrent() {

        int rowCount = table.getItems().size();
        assertNotEquals("No existe ningun cliente",
                rowCount, 0);

        Node tipoRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(0).query();
        Node nombreRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(1).query();
        Node duracionRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(2).query();
        Node ingredientesRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(3).query();
        Node pasosRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(4).query();
        Node preciosRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(5).query();
        Node vegetarianoRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(6).query();
        Node veganoRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(7).query();

        assertNotNull("El cliente es nullo, no se puede editar", nombreRow);

        clickOn(nombreRow);
        Receta recetaSeleccionado = (Receta) table.getSelectionModel()
                .getSelectedItem();
        int selectedIndex = table.getSelectionModel().getSelectedIndex();

        Receta recetaModificado = recetaSeleccionado;
        recetaModificado.setTipoReceta(TipoReceta.Postre);
        recetaModificado.setNombre(generateRandomString());
        recetaModificado.setDuracion(Float.parseFloat(generateRandomNumber(0, 4) + ""));
        recetaModificado.setPasos(generateRandomString());
        recetaModificado.setPrecio(Float.parseFloat(generateRandomNumber(0, 4) + ""));
        recetaModificado.setEsVegetariano(Boolean.TRUE);
        recetaModificado.setEsVegetariano(Boolean.FALSE);

        doubleClickOn(tipoRow);
        doubleClickOn(tipoRow);
        write(recetaModificado.getTipoReceta() + "");
        type(KeyCode.ENTER);

        doubleClickOn(nombreRow);
        doubleClickOn(nombreRow);
        write(recetaModificado.getNombre() + "");
        type(KeyCode.ENTER);

        doubleClickOn(duracionRow);
        doubleClickOn(duracionRow);
        write(recetaModificado.getDuracion() + "");
        type(KeyCode.ENTER);

        doubleClickOn(pasosRow);
        doubleClickOn(pasosRow);
        write(recetaModificado.getPasos() + "");
        type(KeyCode.ENTER);

        doubleClickOn(preciosRow);
        doubleClickOn(preciosRow);
        write(recetaModificado.getPrecio() + "");
        type(KeyCode.ENTER);

        doubleClickOn(vegetarianoRow);
        doubleClickOn(vegetarianoRow);
        write(recetaModificado.getEsVegetariano() + "");
        type(KeyCode.ENTER);

        doubleClickOn(veganoRow);
        doubleClickOn(veganoRow);
        write(recetaModificado.getEsVegano() + "");
        type(KeyCode.ENTER);

        assertEquals("The user has not been modified!!!",
                recetaModificado,
                (Receta) table.getItems().get(selectedIndex));

    }

    @Test
    //@Ignore
    public void test4_handleDeleteRecurrent() {
        int rowCount = table.getItems().size();
        assertNotEquals("No existen recetas, no se puede eliminar nada",
                rowCount, 0);

        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("La tabla que se ha seleccionado es nula", row);
        clickOn(row);
        clickOn(botonEliminar);
        clickOn(ButtonType.OK.getText());
        assertEquals("La receta no se ha eliminado!!!",
                rowCount - 1, table.getItems().size());
    }

    protected int generateRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    protected String generateRandomString() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        int length = generateRandomNumber(8, 16);
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

}
