/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author paula
 */
@XmlRootElement(name = "ingrediente")
public class Ingrediente implements Serializable {

    private Integer id;
    private TipoIngrediente tipoIngrediente;

    private String nombre;
    private float precio;

    private float kCal;

    private float carbohidratos;

    private float proteinas;

    private float grasas;

    private List<Receta> listaRecetas;

    public Ingrediente(Integer id, TipoIngrediente tipoIngrediente, String nombre, float precio, float kCal, float carbohidratos, float proteinas, float grasas, List<Receta> listaRecetas) {
        this.id = id;
        this.tipoIngrediente = tipoIngrediente;
        this.nombre = nombre;
        this.precio = precio;
        this.kCal = kCal;
        this.carbohidratos = carbohidratos;
        this.proteinas = proteinas;
        this.grasas = grasas;
        this.listaRecetas = listaRecetas;
    }

    public Ingrediente() {

    }

    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoIngrediente getTipoIngrediente() {
        return tipoIngrediente;
    }

    public void setTipoIngrediente(TipoIngrediente tipoIngrediente) {
        this.tipoIngrediente = tipoIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getkCal() {
        return kCal;
    }

    public void setkCal(float kCal) {
        this.kCal = kCal;
    }

    public float getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(float carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public float getProteinas() {
        return proteinas;
    }

    public void setProteinas(float proteinas) {
        this.proteinas = proteinas;
    }

    public float getGrasas() {
        return grasas;
    }

    public void setGrasas(float grasas) {
        this.grasas = grasas;
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
