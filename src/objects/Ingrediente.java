/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paula
 */
@XmlRootElement(name = "ingrediente")
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;

    private SimpleIntegerProperty id;
    private SimpleObjectProperty<TipoIngrediente> tipoIngrediente;

    private SimpleStringProperty nombre;

    private SimpleFloatProperty precio;

    private SimpleFloatProperty kcal;

    private SimpleFloatProperty carbohidratos;

    private SimpleFloatProperty proteinas;

    private SimpleFloatProperty grasas;

    private List<Receta> listaRecetas;

    private SimpleObjectProperty<Admin> admin;

    public Ingrediente(Integer id, TipoIngrediente tipoIngrediente, String nombre, Float precio, Float kcal,
            Float carbohidratos, Float proteinas, Float grasas, List<Receta> listaRecetas, Admin admin) {
        this.id = new SimpleIntegerProperty(id);
        this.tipoIngrediente = new SimpleObjectProperty<>(tipoIngrediente);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleFloatProperty(precio);
        this.kcal = new SimpleFloatProperty(kcal);
        this.carbohidratos = new SimpleFloatProperty(carbohidratos);
        this.proteinas = new SimpleFloatProperty(proteinas);
        this.grasas = new SimpleFloatProperty(grasas);
        this.listaRecetas = listaRecetas;
        this.admin = new SimpleObjectProperty<>(admin);
    }

    public Ingrediente() {
        this.id = new SimpleIntegerProperty(0);
        this.tipoIngrediente = new SimpleObjectProperty<>(TipoIngrediente.Carne);
        this.nombre = new SimpleStringProperty("ejemplo");
        this.precio = new SimpleFloatProperty(1);
        this.kcal = new SimpleFloatProperty(1);
        this.carbohidratos = new SimpleFloatProperty(1);
        this.proteinas = new SimpleFloatProperty(1);
        this.grasas = new SimpleFloatProperty(1);
        this.admin = new SimpleObjectProperty<>();
    }

    /*    public Ingrediente() {
        this.id = new SimpleIntegerProperty();
        this.tipoIngrediente = new SimpleObjectProperty<>();
        this.nombre = new SimpleStringProperty();
        this.precio = new SimpleFloatProperty();
        this.kcal = new SimpleFloatProperty();
        this.carbohidratos = new SimpleFloatProperty();
        this.proteinas = new SimpleFloatProperty();
        this.grasas = new SimpleFloatProperty();
        this.admin = new SimpleObjectProperty<>();
    }*/
    public Admin getAdmin() {
        return admin.get();
    }

    public void setAdmin(Admin admin) {
        this.admin.set(admin);
    }

    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public TipoIngrediente getTipoIngrediente() {
        return tipoIngrediente.get();
    }

    public void setTipoIngrediente(TipoIngrediente tipoIngrediente) {
        this.tipoIngrediente.set(tipoIngrediente);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public Float getPrecio() {
        return precio.get();
    }

    public void setPrecio(Float precio) {
        this.precio.set(precio);
    }

    public Float getKcal() {
        return kcal.get();
    }

    public void setKcal(Float kcal) {
        this.kcal.set(kcal);
    }

    public Float getCarbohidratos() {
        return carbohidratos.get();
    }

    public void setCarbohidratos(Float carbohidratos) {
        this.carbohidratos.set(carbohidratos);
    }

    public Float getProteinas() {
        return proteinas.get();
    }

    public void setProteinas(Float proteinas) {
        this.proteinas.set(proteinas);
    }

    public Float getGrasas() {
        return grasas.get();
    }

    public void setGrasas(Float grasas) {
        this.grasas.set(grasas);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingrediente other = (Ingrediente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ingrediente{"
                + "id=" + id.get()
                + ",nombre='" + nombre.get() + '\''
                + ",kcal='" + kcal.get() + '\''
                + ",precio='" + precio.get() + '\''
                + ",carb='" + carbohidratos.get() + '\''
                + '}';
    }

}
