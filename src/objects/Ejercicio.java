/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gaizka
 */
@XmlRootElement(name = "ejercicio")
public class Ejercicio implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private SimpleIntegerProperty ejercicio_id;
    
    private SimpleStringProperty nombre;
    
    private SimpleObjectProperty<TipoEjercicio> tipoEjercicio;
    
    private SimpleStringProperty descripcion;
    
    private SimpleFloatProperty duracion;
    
    private SimpleStringProperty kcalQuemadas;
    
    private SimpleObjectProperty<TipoIntensidad> tipoIntensidad;

    public Ejercicio(Integer ejercicio_id, String nombre, TipoEjercicio tipoEjercicio, String descripcion, Float duracion, String kcalQuemadas, TipoIntensidad tipoIntensidad) {
        this.ejercicio_id = new SimpleIntegerProperty(ejercicio_id);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipoEjercicio = new SimpleObjectProperty<>(tipoEjercicio);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.duracion = new SimpleFloatProperty(duracion);
        this.kcalQuemadas = new SimpleStringProperty(kcalQuemadas);
        this.tipoIntensidad = new SimpleObjectProperty<>(tipoIntensidad);
    }
    
    public Ejercicio() {
        this.ejercicio_id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty("NombreEjercicio");
        this.tipoEjercicio = new SimpleObjectProperty<>(TipoEjercicio.Brazo);
        this.descripcion = new SimpleStringProperty("Descripcion");
        this.duracion = new SimpleFloatProperty();
        this.kcalQuemadas = new SimpleStringProperty("kcalQuemadas");
        this.tipoIntensidad = new SimpleObjectProperty<>(TipoIntensidad.Alta);
    }

    //Setters
    

    public void setEjercicio_id(Integer ejercicio_id) {
        this.ejercicio_id.set(ejercicio_id);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public void setTipoEjercicio(TipoEjercicio tipoEjercicio) {
        this.tipoEjercicio.set(tipoEjercicio);
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    
    public void setDuracion(Float duracion) {
        this.duracion.set(duracion);
    }
    
    public void setKcalQuemadas(String kcalQuemadas) {
        this.kcalQuemadas.set(kcalQuemadas);
    }
    
    public void setTipoIntensidad(TipoIntensidad tipoIntensidad) {
        this.tipoIntensidad.set(tipoIntensidad);
    }
    
    //Getters
    public Integer getEjercicio_id() {
        return ejercicio_id.get();
    }
    
    public String getNombre() {
        return nombre.get();
    }

    public TipoEjercicio getTipoEjercicio() {
        return tipoEjercicio.get();
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public Float getDuracion() {
        return duracion.get();
    }

    public String getKcalQuemadas() {
        return kcalQuemadas.get();
    }

    public TipoIntensidad getTipoIntensidad() {
        return tipoIntensidad.get();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejercicio_id != null ? ejercicio_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.ejercicio_id == null && other.ejercicio_id != null) || (this.ejercicio_id != null && !this.ejercicio_id.equals(other.ejercicio_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ejercicio{"
                + "ejercicio_id=" + ejercicio_id.get()
                + ", nombre='" + nombre.get() + '\''
                + ", tipoEjercicio='" + tipoEjercicio + '\''
                + ", descripcion=" + descripcion
                + ", duracion=" + duracion
                + ", kcalQuemadas='" + kcalQuemadas + '\''
                + ", tipoIntensidad=" + tipoIntensidad
                + '}';
    }
}
