/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paula
 */
@XmlRootElement(name = "receta")
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;

    private SimpleIntegerProperty id;

    private SimpleObjectProperty<TipoReceta> tipoReceta;

    private SimpleStringProperty nombre;

    private SimpleFloatProperty duracion;

    private SimpleBooleanProperty esVegetariano;

    private SimpleBooleanProperty esVegano;

    private SimpleFloatProperty precio;

    private SimpleStringProperty pasos;

    private List<RecetaIngrediente> ingredientes;

    private List<Diario> listaDiariosR;

//    private SimpleObjectProperty<Cliente> cliente;
    public Receta(Integer id, TipoReceta tipoReceta, String nombre, float duracion, boolean esVegetariano, boolean esVegano, float precio, String pasos,
            List<RecetaIngrediente> ingredientes, List<Diario> listaDiariosR) {
        this.id = new SimpleIntegerProperty(id);
        this.tipoReceta = new SimpleObjectProperty(tipoReceta);
        this.nombre = new SimpleStringProperty(nombre);
        this.duracion = new SimpleFloatProperty(duracion);
        this.esVegetariano = new SimpleBooleanProperty(esVegetariano);
        this.esVegano = new SimpleBooleanProperty(esVegano);
        this.precio = new SimpleFloatProperty(precio);
        this.pasos = new SimpleStringProperty(pasos);
        this.ingredientes = ingredientes;
        this.listaDiariosR = listaDiariosR;
//        this.cliente = new SimpleObjectProperty<>(cliente);
    }

    public Receta() {
        this.id = new SimpleIntegerProperty();
        this.tipoReceta = new SimpleObjectProperty(TipoReceta.Principal);
        this.nombre = new SimpleStringProperty("NombreReceta");
        this.duracion = new SimpleFloatProperty();
        this.esVegetariano = new SimpleBooleanProperty(false);
        this.esVegano = new SimpleBooleanProperty(false);
        this.precio = new SimpleFloatProperty(22);
        this.pasos = new SimpleStringProperty("gg");
        this.ingredientes = null;
        this.listaDiariosR = null;
//        this.cliente = new SimpleObjectProperty<>(cliente);
    }

//    public Cliente getCliente() {
//        return cliente.get();
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente.get();
//    }
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public TipoReceta getTipoReceta() {
        return tipoReceta.get();
    }

    public void setTipoReceta(TipoReceta tipoReceta) {
        this.tipoReceta.set(tipoReceta);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public Float getDuracion() {
        return duracion.get();
    }

    public void setDuracion(Float duracion) {
        this.duracion.set(duracion);
    }

    public Boolean getEsVegetariano() {
        return esVegetariano.get();
    }

    public void setEsVegetariano(Boolean esVegetariano) {
        this.esVegetariano.set(esVegetariano);
    }

    public Boolean getEsVegano() {
        return esVegano.get();
    }

    public void setEsVegano(Boolean esVegano) {
        this.esVegano.set(esVegano);
    }

    public Float getPrecio() {
        return precio.get();
    }

    public void setPrecio(Float precio) {
        this.precio.set(precio);
    }

    public String getPasos() {
        return pasos.get();
    }

    public void setPasos(String pasos) {
        this.pasos.set(pasos);
    }

    public List<RecetaIngrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<RecetaIngrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Diario> getListaDiariosR() {
        return listaDiariosR;
    }

    public void setListaDiariosR(List<Diario> listaDiariosR) {
        this.listaDiariosR = listaDiariosR;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (super.getClass() != null ? getClass().hashCode() : 0);
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
        final Receta other = (Receta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Receta{"
                + "id=" + id.get()
                + ", tipoReceta=" + tipoReceta.get()
                + ", nombre='" + nombre.get() + '\''
                + ", duracion=" + duracion.get()
                + ", esVegetariano=" + esVegetariano.get()
                + ", esVegano=" + esVegano.get()
                + ", precio=" + precio.get()
                + ", pasos='" + pasos.get() + '\''
                + ", ingredientes=" + ingredientes
                + ", listaDiariosR=" + listaDiariosR
                + '}';
    }
}
