/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bussinesLogic.DiarioFactory;
import bussinesLogic.EjercicioFactory;
import bussinesLogic.RecetaFactory;
import exceptions.BusinessLogicException;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import javax.ws.rs.core.GenericType;
import objects.Cliente;
import objects.Diario;
import objects.Ejercicio;
import objects.Ingrediente;
import objects.Receta;
import objects.RecetaIngrediente;
import objects.TipoReceta;
import objects.Usuario;

/**
 *
 * @author bayro
 */
public class DiarioController {

    private Stage stage;

    private static final Logger LOGGER = Logger.getLogger("DiarioController.class");

    @FXML
    private TableView<Ejercicio> tablaDiarioEjercicios;
    @FXML
    private TableView<Receta> tablaRecetas;

    @FXML
    private TableColumn<Receta, String> columnaNombreR;
    @FXML
    private TableColumn<Receta, TipoReceta> columnaTipo;
    @FXML
    private TableColumn<Receta, List<RecetaIngrediente>> columnaIngredientes;
    @FXML
    private TableColumn<Receta, Float> columnaPrecio;
    @FXML
    private TableColumn<Receta, Boolean> columnaAñadirR;

    @FXML
    private TableColumn<Ejercicio, String> columnaNombre;
    @FXML
    private TableColumn<Ejercicio, String> columnaDescripcion;
    @FXML
    private TableColumn<Ejercicio, Float> columnaDuracion;
    @FXML
    private TableColumn<Ejercicio, String> columnaKcalQuemadas;
    @FXML
    private TableColumn<Ejercicio, Boolean> columnaAñadir;
    @FXML
    private DatePicker fechaDiario;

    private Usuario cliente;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setUsuario(Usuario cliente) {
        this.cliente = cliente;
    }
    private ObservableList<Ejercicio> informacionEjercicios;
    private ObservableList<Receta> informacionRecetas;

    private ObservableList<Diario> informacionDiario;

    private List<Ejercicio> ejerciciosEnDiario;
    private List<Receta> recetasEnDiario;
    private Diario diario = new Diario();

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Diario");
        stage.setResizable(false);

        ejerciciosEnDiario = new ArrayList<>();
        recetasEnDiario = new ArrayList<>();
        fechaDiario.valueProperty().addListener(this::recogerDiario);

        //Try para llamar al metodo que recoge las recetas
        try {
            //Recojo los datos de ejercicios y los guardo en el observableList
            informacionRecetas = FXCollections.observableArrayList(RecetaFactory.getModelo().listaRecetas(new GenericType<List<Receta>>() {
            }));
        } catch (BusinessLogicException ex) {
            //Si algo va mal lo muestro en un alert
            Alert alert = new Alert(Alert.AlertType.ERROR, "La informacion no ha podido ser cargada.");
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaDiarioEjercicios.refresh();
        }
        for (Receta rec : informacionRecetas) {
            LOGGER.info(rec.toString());
        }
        //Cargo y configuro la tabla recetas
        tablaDiarioEjercicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaNombreR.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaTipo.setCellValueFactory(new PropertyValueFactory("tipoReceta"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        columnaAñadirR.setCellValueFactory(param -> {

            SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(param.getValue() != null && diario != null && diario.getListaEjercicios() != null
                    && diario.getListaEjercicios().stream().anyMatch(infoEjercicio -> param.getValue().getId().equals(infoEjercicio.getId())));
            Receta receta = param.getValue();

            booleanProperty.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {

                try {
                    if (newValue) {
                        recetasEnDiario.add(receta);
                        if (diario.getId() == null) {
                            LOGGER.info("Entra al id null");
                            ZoneId zoneId = ZoneId.systemDefault();
                            // Combinar LocalDate con LocalTime a medianoche
                            Date date = Date.from(fechaDiario.getValue().atStartOfDay(zoneId).toInstant());
                            diario.setDia(date);
                            diario.setCliente((Cliente) cliente);

                            diario.setListaRecetas(recetasEnDiario);
                            diario.setComentarios("prueba");
                            ejerciciosEnDiario.forEach((ejer) -> {
                                LOGGER.info("datos dentro de if null" + ejer.toString());
                            });
                        }
                        ejerciciosEnDiario.forEach((ejer) -> {
                            LOGGER.info("datos dentro de if" + ejer.toString());
                        });
                        diario.setListaRecetas(recetasEnDiario);
                        DiarioFactory.getModelo().actualizarDiario(diario);

                        String fecha = fechaDiario.getValue().toString();
                        diario = DiarioFactory.getModelo().buscarPorFecha(new GenericType<Diario>() {
                        }, fecha, cliente.getUser_id());

                    } else {

                        recetasEnDiario.remove(receta);

                        LOGGER.info("Entra al else ");

                        diario.setListaRecetas(recetasEnDiario);
                        DiarioFactory.getModelo().actualizarDiario(diario);
                        tablaDiarioEjercicios.refresh();
                    }
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(DiarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            return booleanProperty;
        });
        // Configura el CellFactory para la columna
        columnaAñadirR.setCellFactory(CheckBoxTableCell.forTableColumn(columnaAñadirR));
        // Configurar las celdas de la columna de ingredientes para ser editables
        columnaIngredientes.setCellFactory(column -> new IngredienteTableCell());

        // Configurar cómo mostrar la lista de ingredientes en la celda
        columnaIngredientes.setCellValueFactory(cellData
                -> new SimpleObjectProperty<List<RecetaIngrediente>>(cellData.getValue().getIngredientes())
        );

        //Añado los objetos ejercicios a la tabla
        tablaRecetas.setItems(informacionRecetas);
        //Hago la tabla editable
        tablaRecetas.setEditable(true);

        //Try para llamar al metodo que recoge los ejercicios
        try {
            //Recojo los datos de ejercicios y los guardo en el observableList
            informacionEjercicios = FXCollections.observableArrayList(EjercicioFactory.getModelo().findAll(new GenericType<List<Ejercicio>>() {
            }));
        } catch (BusinessLogicException ex) {
            //Si algo va mal lo muestro en un alert
            Alert alert = new Alert(Alert.AlertType.ERROR, "La informacion no ha podido ser cargada.");
            alert.show();
            LOGGER.log(Level.SEVERE, ex.getMessage());
            tablaDiarioEjercicios.refresh();
        }

        //Cargo y configuro la tabla ejercicios
        tablaDiarioEjercicios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        columnaDuracion.setCellValueFactory(new PropertyValueFactory("duracion"));
        columnaKcalQuemadas.setCellValueFactory(new PropertyValueFactory("kcalQuemadas"));
        columnaAñadir.setCellValueFactory(param -> {
            SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(param.getValue() != null && diario != null && diario.getListaEjercicios() != null
                    && diario.getListaEjercicios().stream().anyMatch(infoEjercicio -> param.getValue().getId().equals(infoEjercicio.getId())));
            Ejercicio ejercicio = param.getValue();

            booleanProperty.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {

                LOGGER.info("Estado del checkbox para el ejercicio " + ejercicio.getNombre() + ": " + newValue);
                try {
                    if (newValue) {
                        ejerciciosEnDiario.add(ejercicio);
                        if (diario.getId() == null) {
                            LOGGER.info("Entra al id null");
                            ZoneId zoneId = ZoneId.systemDefault();
                            // Combinar LocalDate con LocalTime a medianoche
                            Date date = Date.from(fechaDiario.getValue().atStartOfDay(zoneId).toInstant());
                            diario.setDia(date);
                            diario.setCliente((Cliente) cliente);

                            diario.setListaEjercicios(ejerciciosEnDiario);
                            diario.setComentarios("prueba");
                            ejerciciosEnDiario.forEach((ejer) -> {
                                LOGGER.info("datos dentro de if null" + ejer.toString());
                            });
                        }
                        ejerciciosEnDiario.forEach((ejer) -> {
                            LOGGER.info("datos dentro de if" + ejer.toString());
                        });
                        diario.setListaEjercicios(ejerciciosEnDiario);
                        DiarioFactory.getModelo().actualizarDiario(diario);

                        String fecha = fechaDiario.getValue().toString();
                        diario = DiarioFactory.getModelo().buscarPorFecha(new GenericType<Diario>() {
                        }, fecha, cliente.getUser_id());

                    } else {
                        LOGGER.info(ejercicio.toString());
                        ejerciciosEnDiario.remove(ejercicio);

                        LOGGER.info("Entra al else ");

                        diario.setListaEjercicios(ejerciciosEnDiario);
                        DiarioFactory.getModelo().actualizarDiario(diario);
                        tablaDiarioEjercicios.refresh();
                    }
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(DiarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            return booleanProperty;
        });

        // Configura el CellFactory para la columna
        columnaAñadir.setCellFactory(CheckBoxTableCell.forTableColumn(columnaAñadir));

        //Añado los objetos ejercicios a la tabla
        tablaDiarioEjercicios.setItems(informacionEjercicios);
        //Hago la tabla editable
        tablaDiarioEjercicios.setEditable(true);

        //Logica para cuando el checkbox se clicke o desclicke
        stage.show();
        LOGGER.info("Diario iniciado!");
    }

    public void recogerDiario(ObservableValue observable, LocalDate viejaFecha, LocalDate nuevaFecha) {
        if (nuevaFecha != null) {
            //Try para llamar al metodo que recoge el diario

            try {
                LOGGER.info("Entra a recoger!");
                String fecha = fechaDiario.getValue().toString();

                diario = DiarioFactory.getModelo().buscarPorFecha(new GenericType<Diario>() {
                }, fecha, cliente.getUser_id());
                ejerciciosEnDiario = diario.getListaEjercicios();
                recetasEnDiario = diario.getListaRecetas();
                LOGGER.info(diario.toString());
                tablaDiarioEjercicios.refresh();
            } catch (BusinessLogicException ex) {
                ejerciciosEnDiario.clear();
                recetasEnDiario.clear();
                diario = new Diario();
                tablaDiarioEjercicios.refresh();
            }
        }
    }

    public Diario buscarEjercicios(Integer id) {
        //Try para llamar al metodo que recoge el diario
        try {
            String fecha = fechaDiario.getValue().toString();

            diario = DiarioFactory.getModelo().buscarEjercicio(new GenericType<Diario>() {
            }, fecha, cliente.getUser_id(), id);
            LOGGER.info(diario.toString());
        } catch (BusinessLogicException ex) {
            //Si algo va mal lo muestro en un alert
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return diario;
    }

    public int verficiarEjercicio() {
        return 0;
    }
}
