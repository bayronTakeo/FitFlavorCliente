/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author paula
 */
@XmlRootElement(name = "recetas")
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private TipoReceta tipoReceta;

    private String nombre;

    private float duracion;

    private boolean esVegetariano;

    private boolean esVegano;

    private float precio;

    private String pasos;

    private List<Ingrediente> ingredientes;

    private List<Diario> listaDiariosR;

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Receta(Integer id, TipoReceta tipoReceta, String nombre, float duracion, boolean esVegetariano, boolean esVegano, float precio, List<Ingrediente> ingredientes, List<Diario> listaDiariosR) {
        this.id = id;
        this.tipoReceta = tipoReceta;
        this.nombre = nombre;
        this.duracion = duracion;
        this.esVegetariano = esVegetariano;
        this.esVegano = esVegano;
        this.precio = precio;
        this.ingredientes = ingredientes;
        this.listaDiariosR = listaDiariosR;
    }

    public Receta() {

    }

    public List<Diario> getListaDiariosR() {
        return listaDiariosR;
    }

    public void setListaDiariosR(List<Diario> listaDiariosR) {
        this.listaDiariosR = listaDiariosR;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public TipoReceta getTipoReceta() {
        return tipoReceta;
    }

    public void setTipoReceta(TipoReceta tipoReceta) {
        this.tipoReceta = tipoReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public boolean isEsVegetariano() {
        return esVegetariano;
    }

    public void setEsVegetariano(boolean esVegetariano) {
        this.esVegetariano = esVegetariano;
    }

    public boolean isEsVegano() {
        return esVegano;
    }

    public void setEsVegano(boolean esVegano) {
        this.esVegano = esVegano;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
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
        return super.toString();
    }

}
