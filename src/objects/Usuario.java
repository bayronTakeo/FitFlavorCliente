/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Esta clase contiene toda la informacion del usuario
 *
 * @author Bayron
 */
@XmlRootElement(name = "usuario")
@XmlSeeAlso({Cliente.class, Admin.class})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private SimpleIntegerProperty user_id;

    private SimpleStringProperty email;

    private SimpleStringProperty nombreCompleto;

    private ObjectProperty<Date> fechaNacimiento;

    private SimpleStringProperty telefono;

    private SimpleStringProperty direccion;

    private SimpleStringProperty codigoPostal;

    private SimpleStringProperty contrasenia;

    private SimpleObjectProperty<EnumPrivilegios> privilegio;

    public Usuario(Integer user_id, String email, String nombreCompleto, Date fechaNacimiento, String telefono, String direccion, String codigoPostal, String contrasenia, EnumPrivilegios privilegio) {
        this.user_id = new SimpleIntegerProperty(user_id);
        this.email = new SimpleStringProperty(email);
        this.nombreCompleto = new SimpleStringProperty(nombreCompleto);
        this.fechaNacimiento = new SimpleObjectProperty<>(fechaNacimiento);
        this.telefono = new SimpleStringProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.codigoPostal = new SimpleStringProperty(codigoPostal);
        this.contrasenia = new SimpleStringProperty(contrasenia);
        this.privilegio = new SimpleObjectProperty<>(privilegio);
    }

    public Usuario() {
        this.user_id = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty();
        this.nombreCompleto = new SimpleStringProperty();
        this.fechaNacimiento = new SimpleObjectProperty<>();
        this.telefono = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.codigoPostal = new SimpleStringProperty();
        this.contrasenia = new SimpleStringProperty();
        this.privilegio = new SimpleObjectProperty<>();
    }

    // Setters
    public void setUser_id(Integer user_id) {
        this.user_id.set(user_id);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto.set(nombreCompleto);
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento.set(fechaNacimiento);
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal.set(codigoPostal);
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia.set(contrasenia);
    }

    public void setPrivilegio(EnumPrivilegios privilegio) {
        this.privilegio.set(privilegio);
    }

    // Getters
    public Integer getUser_id() {
        return user_id.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getNombreCompleto() {
        return nombreCompleto.get();
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento.get();
    }

    public String getTelefono() {
        return telefono.get();
    }

    public String getDireccion() {
        return direccion.get();
    }

    public String getCodigoPostal() {
        return codigoPostal.get();
    }

    public String getContrasenia() {
        return contrasenia.get();
    }

    public EnumPrivilegios getPrivilegio() {
        return privilegio.get();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (user_id != null ? user_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.user_id == null && other.user_id != null) || (this.user_id != null && !this.user_id.equals(other.user_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "user_id=" + user_id.get()
                + ", email='" + email.get() + '\''
                + ", nombreCompleto='" + nombreCompleto + '\''
                + ", fechaNacimiento=" + fechaNacimiento
                + ", telefono=" + telefono
                + ", direccion='" + direccion + '\''
                + ", codigoPostal=" + codigoPostal
                + ", contrasenia='" + contrasenia + '\''
                + ", privilegio=" + privilegio
                + '}';
    }

}
