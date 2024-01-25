    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaTablas;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javafx.util.StringConverter;

/**
 *
 * @author bayron
 */
public class floatFormateador extends StringConverter<Float> {

    private final NumberFormat nf;

    public floatFormateador() {
        nf = NumberFormat.getInstance(Locale.getDefault());
    }

    @Override
    public String toString(Float value) {
        try {
            return nf.format(value);
        } catch (IllegalArgumentException ex) {
            return null;
        }

    }

    @Override
    public Float fromString(String string) {
        try {
            return nf.parse(string).floatValue();
        } catch (ParseException ex) {
            return null;
        }
    }
}