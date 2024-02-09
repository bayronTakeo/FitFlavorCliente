/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bayron
 */
@XmlRootElement(name = "admin")
public class Admin extends Usuario {

    private SimpleObjectProperty<List<Receta>> recetas;
    private SimpleObjectProperty<List<Ingrediente>> ingredientes;

    public Admin() {
        super(0, "", "usuario", null, "123", "ejemplo", "123", "abcd*1234", EnumPrivilegios.ADMIN);

    }
}
