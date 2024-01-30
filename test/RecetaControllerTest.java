/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.MalformedURLException;
import static java.rmi.Naming.lookup;
import java.rmi.NotBoundException;
import static org.hibernate.criterion.Projections.rowCount;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.UserType;
import objects.Receta;
import org.apache.lucene.search.FilteredQuery;
import static org.hibernate.criterion.Projections.rowCount;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author paula
 */
public class RecetaControllerTest extends ApplicationTest {

    private Stage stage;

    private static final Logger LOGGER = Logger.getLogger("RecetaController.class");

    private ContextMenu menuTabla;

    private TableView tablaRecetas;

    private TableColumn<Receta, String> columnaTipo;

    private TableColumn<Receta, String> columnaNombre;

    private TableColumn<Receta, Float> columnaDuracion;

    private TableColumn<Receta, List> columnaIngrediente;

    private TableColumn<Receta, String> columnaPasos;

    private TableColumn<Receta, Float> columnaPrecio;

    private TableColumn<Receta, Boolean> columnaVegetariano;

    private TableColumn<Receta, Boolean> columnaVegano;

    private Button botonAgregar, botonEliminar, botonEditar;

    private ObservableList<Receta> informacionRecetas;

    @Test
    public void actualizarReceta() {
        clickOn(tablaRecetas).clickOn("NombreReceta");
        clickOn(columnaNombre);
        columnaNombre.clear();
        write("receta");
        clickOn(columnaDuracion);
        columnaDuracion.clear();
        write("200");
        columnaPasos.clear();
        write("Pasos de la receta");
        clickOn(bEditar);
        clickOn("Aceptar");
        List<Receta> sede = tablaRecetas.getItems();
        assertEquals("Receta modificada",
                sede.stream().filter(u -> u.getNombre().equals("receta")).count(), 1);
    }

    @Test
    public void borrarReceta() {
        int rowCount = tablaRecetas.getItems().size();
        clickOn(tablaRecetas).clickOn("receta");
        clickOn(botonEliminar);
        clickOn("Aceptar");
        List<Receta> sede = tablaRecetas.getItems();
        assertEquals("Receta eliminada", rowCount - 1, sede.size());
    }

}
