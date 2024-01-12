/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase contiene toda la informacion del usuario
 *
 * @author Bayron
 */
@XmlRootElement(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private SimpleIntegerProperty user_id;

    private SimpleStringProperty email;

    private SimpleStringProperty nombreCompleto;

    private ObjectProperty<LocalDate> fechaNacimiento;

    private SimpleIntegerProperty telefono;

    private SimpleStringProperty direccion;

    private SimpleIntegerProperty codigoPostal;

    private SimpleStringProperty contrasenia;

    private SimpleObjectProperty<EnumPrivilegios> privilegio;

    public Usuario(Integer user_id, String email, String nombreCompleto, LocalDate fechaNacimiento, int telefono, String direccion, int codigoPostal, String contrasenia, EnumPrivilegios privilegio) {
        this.user_id = new SimpleIntegerProperty(user_id);
        this.email = new SimpleStringProperty(email);
        this.nombreCompleto = new SimpleStringProperty(nombreCompleto);
        this.fechaNacimiento = new SimpleObjectProperty<>(fechaNacimiento);
        this.telefono = new SimpleIntegerProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.codigoPostal = new SimpleIntegerProperty(codigoPostal);
        this.contrasenia = new SimpleStringProperty(contrasenia);
        this.privilegio = new SimpleObjectProperty<>(privilegio);
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

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento.set(fechaNacimiento);
    }

    public void setTelefono(int telefono) {
        this.telefono.set(telefono);
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public void setCodigoPostal(int codigoPostal) {
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento.get();
    }

    public int getTelefono() {
        return telefono.get();
    }

    public String getDireccion() {
        return direccion.get();
    }

    public int getCodigoPostal() {
        return codigoPostal.get();
    }

    public String getContrasenia() {
        return contrasenia.get();
    }

    public EnumPrivilegios getPrivilegio() {
        return privilegio.get();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Usuario() {

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
        return super.toString();
    }

}
