package controllers;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.util.converter.LocalDateStringConverter;
import objects.Cliente;

public class DatePickerTable extends TableCell<Cliente, Date> {

    private DatePicker datePicker;
    private Date originalValue;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    DatePickerTable() {
    }

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();

            // Crear el DatePicker
            datePicker = new DatePicker();

            // Obtener la fecha actual del modelo
            originalValue = getItem();

            // Configurar la fecha actual en el DatePicker
            if (originalValue != null) {
                datePicker.setValue(originalValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }

            datePicker.setOnAction((e) -> {
                // Convertir la fecha seleccionada y enviarla al modelo
                commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            });
            // Utilizar el formato del sistema operativo para mostrar la fecha
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.SHORT);
            datePicker.setConverter(new LocalDateStringConverter(dateFormatter, dateFormatter));

            setText(null);
            setGraphic(datePicker);
        }
    }

    @Override
    public void updateItem(Date item, boolean empty) {
        super.updateItem(item, empty);
        //The pattern for the date format should be read from a properties´ file

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                setText(null);
                setGraphic(datePicker);
            } else if (item != null) {
                String date = dateFormatter.format(item);
                setText(date);
                setGraphic(null);
            }
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(null);
        setText(originalValue != null ? dateFormatter.format(originalValue) : null);
    }
}
