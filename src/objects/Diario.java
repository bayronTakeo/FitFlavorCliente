/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase guarda una lista de dias, ejercicios y recetas
 *
 * @author bayron
 */
@XmlRootElement(name = "diario")
public class Diario implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Campo identificador para el Diario.
     */

    private SimpleIntegerProperty id;
    /**
     * Lista de Ejercicios.
     */

    private List<Ejercicio> listaEjercicios;
    /**
     * Lista de Recetas
     */

    private List<Receta> listaRecetas;

    private SimpleObjectProperty<Cliente> cliente;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dia;

    private String comentarios;

    public Diario(Integer id, List<Ejercicio> listaEjercicios, List<Receta> listaRecetas, Cliente cliente, Date dia, String comentarios) {
        this.id = new SimpleIntegerProperty(id);
        this.listaEjercicios = listaEjercicios;
        this.listaRecetas = listaRecetas;
        this.cliente = new SimpleObjectProperty<>(cliente);
        this.dia = dia;
        this.comentarios = comentarios;
    }

    public Cliente getCliente() {
        return cliente.get();
    }

    public Date getDia() {
        return dia;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setCliente(Cliente cliente) {
        this.cliente.set(cliente);
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Diario() {
    }

    /**
     *
     * @return the id
     */
    public Integer getId() {
        return id.get();
    }

    /**
     *
     * @param id the id to be set
     */
    public void setId(Integer id) {
        this.id.set(id);
    }

    /**
     *
     * @return the ListaEjercicios
     */
    public List<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    /**
     *
     * @param listaEjercicios the listaEjercicios to set
     */
    public void setListaEjercicios(List<Ejercicio> listaEjercicios) {
        this.listaEjercicios = listaEjercicios;
    }

    /**
     *
     * @return the ListaRecetas
     */
    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    /**
     *
     * @param listaRecetas the listaEjercicios to set
     */
    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Diario)) {
            return false;
        }
        Diario other = (Diario) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Diario{"
                + "id=" + id
                + ", cliente=" + (cliente != null ? cliente.get() : "null")
                + ", dia=" + dia
                + ", comentarios='" + comentarios + '\''
                + ", listaEjercicios=" + listaEjercicios
                + ", listaRecetas=" + listaRecetas
                + '}';
    }

}
