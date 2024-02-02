package cliente;

import bussinesLogic.ClienteFactory;
import bussinesLogic.ClienteInterfaz;
import bussinesLogic.IngredienteFactory;
import bussinesLogic.IngredienteInterfaz;
import bussinesLogic.UsuarioFactory;
import bussinesLogic.UsuarioInterfaz;
import controllers.IngredientesAdminController;
import controllers.administradorClientesController;
import exceptions.BusinessLogicException;
import files.AsymmetricCliente;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import javax.xml.bind.DatatypeConverter;
import objects.Cliente;
import objects.Ingrediente;
import objects.Usuario;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author Bayron.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IngredienteController_Test extends ApplicationTest {

    private ObservableList<Ingrediente> ingrediente;
    private Usuario usuario;

    private IngredienteInterfaz ri = IngredienteFactory.getModelo();

    private UsuarioInterfaz usuIn = UsuarioFactory.getModelo();
    private Button botonAgregar, botonEliminar, botonEditar, botonBuscar, botonFiltros, btnReport;
    private TextField tfSearch;
    private TableView table;

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        byte[] passwordBytes = new AsymmetricCliente().cipher("abcd*1234");
        usuario = usuIn.signIn(new GenericType<Cliente>() {
        }, "admin@gmail.com", DatatypeConverter.printHexBinary(passwordBytes));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IngredientesAdmin.fxml"));
        System.out.println(loader.getLocation());
        Parent root = loader.load();
        IngredientesAdminController administrador = loader.getController();
        administrador.setStage(stage);
        administrador.setCliente(usuario);

        administrador.initStage(root);

        this.stage = stage;

        botonAgregar = lookup("#botonAgregar").query();
        botonEliminar = lookup("#botonEliminar").query();
        botonEditar = lookup("#botonEditar").query();
        botonFiltros = lookup("#botonFiltros").query();

        table = lookup("#tablaIngredientes").queryTableView();
    }

    @Test
    @Ignore
    public void test1_initStage() {
        verifyThat(botonAgregar, isVisible());
        verifyThat(botonAgregar, isEnabled());
        verifyThat(botonEliminar, isVisible());
        verifyThat(botonEliminar, isEnabled());
        verifyThat(botonEditar, isVisible());
        verifyThat(botonEditar, isEnabled());
        // verifyThat(botonFiltros, isVisible());
        //verifyThat(botonFiltros, isEnabled());
//        verifyThat(btnReport, isVisible());
//        verifyThat(btnReport, isEnabled());

    }

    @Test
    @Ignore
    public void createIngredient() {
        try {
            int rowCount = table.getItems().size();

            clickOn(botonAgregar);
            System.out.println(table.getItems().size());
            assertEquals("La fila no se ha creado!!!", rowCount + 1, table.getItems().size());
            List<Ingrediente> ingredientes = ri.findAll(new GenericType<List<Ingrediente>>() {
            });

            Ingrediente ingredienteServer = ingredientes.get(ingredientes.size() - 1);
            List<Ingrediente> ingredienteTabla = table.getItems();

            assertEquals("El recurrente no se ha añdido!!!",
                    ingredienteTabla.stream().filter(c -> c.getId().equals(ingredienteServer.getId())).count(), 1);

        } catch (BusinessLogicException ex) {
            Logger.getLogger(ClienteController_Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    @Ignore
    public void test3_handleModifyRecurrent() {

        int rowCount = table.getItems().size();
        assertNotEquals("No existe ningun cliente",
                rowCount, 0);

        Node tipoRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(0).query();
        Node nombreRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(1).query();
        Node precioRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(2).query();
        Node kiloRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(3).query();
        Node carboRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(4).query();
        Node proteinasRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(5).query();
        Node grasasRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(6).query();

        assertNotNull("El cliente es nullo, no se puede editar", nombreRow);

        clickOn(nombreRow);
        Ingrediente ingredienteSeleccionado = (Ingrediente) table.getSelectionModel()
                .getSelectedItem();
        int selectedIndex = table.getSelectionModel().getSelectedIndex();

        Ingrediente ingredienteModificado = ingredienteSeleccionado;
        ingredienteModificado.setId(ingredienteSeleccionado.getId());
        ingredienteModificado.setNombre(generateRandomString());
        ingredienteModificado.setPrecio(Float.parseFloat(generateRandomNumber(0, 4) + ""));
        ingredienteModificado.setKcal(Float.parseFloat(generateRandomNumber(0, 4) + ""));
        ingredienteModificado.setCarbohidratos(Float.parseFloat(generateRandomNumber(0, 4) + ""));
        ingredienteModificado.setProteinas(Float.parseFloat(generateRandomNumber(0, 4) + ""));
        ingredienteModificado.setGrasas(Float.parseFloat(generateRandomNumber(0, 4) + ""));

        doubleClickOn(nombreRow);
        doubleClickOn(nombreRow);
        write(ingredienteModificado.getNombre());
        type(KeyCode.ENTER);

        doubleClickOn(precioRow);
        doubleClickOn(precioRow);
        write(ingredienteModificado.getPrecio() + "");
        type(KeyCode.ENTER);

        doubleClickOn(kiloRow);
        doubleClickOn(kiloRow);
        write(ingredienteModificado.getKcal() + "");
        type(KeyCode.ENTER);

        doubleClickOn(carboRow);
        doubleClickOn(carboRow);
        write(ingredienteModificado.getCarbohidratos() + "");
        type(KeyCode.ENTER);

        doubleClickOn(proteinasRow);
        doubleClickOn(proteinasRow);
        write(ingredienteModificado.getProteinas() + "");
        type(KeyCode.ENTER);

        doubleClickOn(grasasRow);
        doubleClickOn(grasasRow);
        write(ingredienteModificado.getGrasas() + "");
        type(KeyCode.ENTER);

        assertEquals("The user has not been modified!!!",
                ingredienteModificado,
                (Ingrediente) table.getItems().get(selectedIndex));

    }

    @Test

    public void test4_handleDeleteRecurrent() {
        int rowCount = table.getItems().size();
        assertNotEquals("No existen ingredientes, no se puede eliminar nada",
                rowCount, 0);

        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("La tabla que se ha seleccionado es nula", row);
        clickOn(row);
        clickOn(botonEliminar);
        clickOn(ButtonType.OK.getText());
        assertEquals("El ingrediente no se ha eliminado!!!",
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
