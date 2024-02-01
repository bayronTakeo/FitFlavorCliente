/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gaizka
 */
@XmlRootElement(name = "ejercicio")
public class Ejercicio implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger("Ejercicio.class");
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private SimpleIntegerProperty id;

    private SimpleStringProperty nombre;

    private SimpleObjectProperty<TipoEjercicio> tipoEjercicio;

    private SimpleStringProperty descripcion;

    private SimpleFloatProperty duracion;

    private SimpleStringProperty kcalQuemadas;

    private SimpleObjectProperty<TipoIntensidad> tipoIntensidad;

    private List<Diario> listaDiariosE;

    private SimpleObjectProperty<Admin> admin;

    public Ejercicio(Integer id, String nombre, TipoEjercicio tipoEjercicio, String descripcion, Float duracion, String kcalQuemadas, TipoIntensidad tipoIntensidad, List<Diario> listaDiariosE, Admin admin) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.tipoEjercicio = new SimpleObjectProperty<>(tipoEjercicio);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.duracion = new SimpleFloatProperty(duracion);
        this.kcalQuemadas = new SimpleStringProperty(kcalQuemadas);
        this.tipoIntensidad = new SimpleObjectProperty<>(tipoIntensidad);
        this.listaDiariosE = listaDiariosE;
        this.admin = new SimpleObjectProperty<>(admin);
    }

    public Ejercicio(Admin admin) {
        this.id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty("nom");
        this.tipoEjercicio = new SimpleObjectProperty<>(TipoEjercicio.Brazo);
        this.descripcion = new SimpleStringProperty("desc");
        this.duracion = new SimpleFloatProperty();
        this.kcalQuemadas = new SimpleStringProperty("100");
        this.tipoIntensidad = new SimpleObjectProperty<>(TipoIntensidad.Alta);
        this.admin = new SimpleObjectProperty<>(admin);
        LOGGER.info(this.toString());
    }

    public Ejercicio() {
        this.id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.tipoEjercicio = new SimpleObjectProperty<>();
        this.descripcion = new SimpleStringProperty();
        this.duracion = new SimpleFloatProperty();
        this.kcalQuemadas = new SimpleStringProperty();
        this.tipoIntensidad = new SimpleObjectProperty<>();
        this.admin = new SimpleObjectProperty<>();
    }

    public void setListaDiariosE(List<Diario> listaDiariosE) {
        this.listaDiariosE = listaDiariosE;
    }

    public List<Diario> getListaDiariosE() {
        return listaDiariosE;
    }

    public Admin getAdmin() {
        return admin.get();
    }

    public void setAdmin(Admin admin) {
        this.admin.set(admin);
    }

    //Setters
    public void setId(Integer id) {
        this.id.set(id);
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
    public Integer getId() {
        return id.get();
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejercicio)) {
            return false;
        }
        Ejercicio other = (Ejercicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ejercicio{"
                + "ejercicio_id=" + id.get()
                + ", nombre='" + nombre.get() + '\''
                + ", tipoEjercicio='" + tipoEjercicio + '\''
                + ", descripcion=" + descripcion
                + ", duracion=" + duracion
                + ", kcalQuemadas='" + kcalQuemadas + '\''
                + ", admin='" + admin.get() + '\''
                + ", tipoIntensidad=" + tipoIntensidad
                + '}';
    }
}
