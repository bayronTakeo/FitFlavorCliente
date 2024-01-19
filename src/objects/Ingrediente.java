/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.List;
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

    private SimpleFloatProperty kCal;

    private SimpleFloatProperty carbohidratos;

    private SimpleFloatProperty proteinas;

    private SimpleFloatProperty grasas;

    private List<Receta> listaRecetas;

    public Ingrediente(Integer id, TipoIngrediente tipoIngrediente, String nombre, Float precio, Float kCal, Float carbohidratos, Float proteinas, Float grasas, List<Receta> listaRecetas) {
        this.id = new SimpleIntegerProperty(id);
        this.tipoIngrediente = new SimpleObjectProperty<>(tipoIngrediente);
        this.nombre = new SimpleStringProperty(nombre);
        this.precio = new SimpleFloatProperty(precio);
        this.kCal = new SimpleFloatProperty(kCal);
        this.carbohidratos = new SimpleFloatProperty(carbohidratos);
        this.proteinas = new SimpleFloatProperty(proteinas);
        this.grasas = new SimpleFloatProperty(grasas);
        this.listaRecetas = listaRecetas;
    }

    public Ingrediente() {
        this.id = new SimpleIntegerProperty(0);
        this.tipoIngrediente = new SimpleObjectProperty<>(TipoIngrediente.Carne);
        this.nombre = new SimpleStringProperty("ejemplo");
        this.precio = new SimpleFloatProperty(1);
        this.kCal = new SimpleFloatProperty(1);
        this.carbohidratos = new SimpleFloatProperty(1);
        this.proteinas = new SimpleFloatProperty(1);
        this.grasas = new SimpleFloatProperty(1);

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

    public Float getkCal() {
        return kCal.get();
    }

    public void setkCal(Float kCal) {
        this.kCal.set(kCal);
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
        int hash = 0;
        hash += (super.getClass() != null ? getClass().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((super.getClass() == null && other.getClass() != null) || (super.getClass() != null && !super.getClass().equals(other.getClass()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ingrediente{"
                + "id=" + id.get()
                + ",nombre='" + nombre.get() + '\''
                + '}';
    }

}
