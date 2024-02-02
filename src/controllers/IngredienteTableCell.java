package controllers;

import javafx.scene.control.TableCell;
import objects.RecetaIngrediente;

import java.util.List;
import objects.Receta;

// Clase IngredienteTableCell para manejar la edición de celdas con List<RecetaIngrediente>
public class IngredienteTableCell extends TableCell<Receta, List<RecetaIngrediente>> {

    @Override
    protected void updateItem(List<RecetaIngrediente> item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
        } else {
            setText(obtenerNombresIngredientes(item));
        }
    }

    private String obtenerNombresIngredientes(List<RecetaIngrediente> ingredientes) {
        StringBuilder nombres = new StringBuilder();
        for (RecetaIngrediente ingrediente : ingredientes) {
            nombres.append(ingrediente.getIngrediente()).append(", ");
        }
        // Eliminar la coma adicional al final
        if (nombres.length() > 0) {
            nombres.deleteCharAt(nombres.length() - 2);
        }
        return nombres.toString();
    }
}
