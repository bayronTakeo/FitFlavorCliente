package cliente;

import bussinesLogic.ClienteFactory;
import bussinesLogic.ClienteInterfaz;
import controllers.administradorClientesController;
import exceptions.BusinessLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;
import objects.Cliente;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author Bayron.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClienteController_Test extends ApplicationTest {

    private Cliente client;

    private ClienteInterfaz ri = ClienteFactory.getModelo();

    private Button botonAgregar, botonEliminar, botonEditar, botonBuscar, btnReport;
    private TextField textfieldBuscar;
    private ComboBox cbAtribute, cbCondition;
    private TextField tfSearch;
    private TableView table;

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        client = ClienteFactory.getModelo().buscarCliente(new GenericType<Cliente>() {
        }, "usuario@gmail.com");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/administradorClientes.fxml"));
        System.out.println(loader.getLocation());
        Parent root = loader.load();
        administradorClientesController administrador = loader.getController();
        administrador.setStage(stage);
        administrador.setCliente(client);
        administrador.initStage(root);

        this.stage = stage;

        botonAgregar = lookup("#botonAgregar").query();
        botonEliminar = lookup("#botonEliminar").query();
        botonEditar = lookup("#botonEditar").query();
        botonBuscar = lookup("#botonBuscar").query();
        textfieldBuscar = lookup("#textfieldBuscar").query();
        table = lookup("#tablaUsuarios").queryTableView();
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
        verifyThat(botonBuscar, isVisible());
        verifyThat(botonBuscar, isEnabled());
//        verifyThat(btnReport, isVisible());
//        verifyThat(btnReport, isEnabled());

    }

    @Test
    @Ignore
    public void test2_handleCreateRecurrent() {
        try {
            int rowCount = table.getItems().size();

            clickOn(botonAgregar);
            System.out.println(table.getItems().size());
            assertEquals("La fila no se ha creado!!!", rowCount + 1, table.getItems().size());
            List<Cliente> clientes = ri.findAll(new GenericType<List<Cliente>>() {
            });

            Cliente clienteServer = clientes.get(clientes.size() - 1);
            List<Cliente> clienteTabla = table.getItems();

            assertEquals("El recurrente no se ha añdido!!!",
                    clienteTabla.stream().filter(c -> c.getUser_id().equals(clienteServer.getUser_id())).count(), 1);

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

        Node emailRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(0).query();
        Node nombreRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(1).query();
        Node fechaRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(2).query();
        Node telefonoRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(3).query();
        Node direccionRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(4).query();
        Node codigoRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(5).query();
        Node contraseñaRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(6).query();

        assertNotNull("El cliente es nullo, no se puede editar", emailRow);

        clickOn(emailRow);
        Cliente clienteSeleccionado = (Cliente) table.getSelectionModel()
                .getSelectedItem();
        int selectedIndex = table.getSelectionModel().getSelectedIndex();

        Cliente clienteModificado = clienteSeleccionado;
        clienteModificado.setUser_id(clienteSeleccionado.getUser_id());
        clienteModificado.setNombreCompleto(generateRandomString());
        clienteModificado.setDireccion(generateRandomString());
        clienteModificado.setTelefono(String.valueOf(generateRandomNumber(0, 999999)));

        clienteModificado.setCodigoPostal(String.valueOf(generateRandomNumber(0, 999999)));

        doubleClickOn(nombreRow);
        doubleClickOn(nombreRow);
        write(clienteModificado.getNombreCompleto());
        type(KeyCode.ENTER);

        doubleClickOn(emailRow);
        doubleClickOn(emailRow);
        doubleClickOn(emailRow);
        write(clienteModificado.getEmail());
        type(KeyCode.ENTER);

        doubleClickOn(telefonoRow);
        doubleClickOn(telefonoRow);
        write(clienteModificado.getTelefono());
        type(KeyCode.ENTER);

        doubleClickOn(direccionRow);
        doubleClickOn(direccionRow);
        write(clienteModificado.getDireccion());
        type(KeyCode.ENTER);

        clickOn(codigoRow);
        clickOn(codigoRow);
        clickOn(clienteModificado.getCodigoPostal());
        type(KeyCode.ENTER);

        assertEquals("The user has not been modified!!!",
                clienteModificado,
                (Cliente) table.getItems().get(selectedIndex));

    }

    @Test
    @Ignore
    public void test4_handleDeleteRecurrent() {
        int rowCount = table.getItems().size();
        assertNotEquals("No existen clientes, no se puede eliminar nada",
                rowCount, 0);

        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("La tabla que se ha seleccionado es nula", row);
        clickOn(row);
        clickOn(botonEliminar);
        clickOn(ButtonType.OK.getText());
        assertEquals("El Recurrente no se ha eliminado!!!",
                rowCount - 1, table.getItems().size());
    }

    @Test
    //@Ignore
    public void test_buscar() {
        int rowCount = table.getItems().size();
        clickOn("#textfieldBuscar");
        Node nombreRow = lookup(".table-row-cell").nth(0).lookup(".table-cell").nth(1).query();

        String nombre = ((Labeled) nombreRow).getText();

        write(nombre);

        clickOn(botonBuscar);
        int rowCount2 = table.getItems().size();

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
