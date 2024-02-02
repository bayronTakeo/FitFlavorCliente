/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 *
 * @author bayro
 */
public class RecetaIngrediente {

    @EmbeddedId
    private RecetaIngredienteId recetaIngredienteId;

    private Receta receta;

    private Ingrediente ingrediente;

    private Float cantidad;

    public RecetaIngrediente(RecetaIngredienteId recetaIngredienteId, Receta receta, Ingrediente ingrediente, Float cantidad) {
        this.recetaIngredienteId = recetaIngredienteId;
        this.receta = receta;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public RecetaIngrediente() {
    }

    public void setRecetaIngredienteId(RecetaIngredienteId recetaIngredienteId) {
        this.recetaIngredienteId = recetaIngredienteId;
    }

    public RecetaIngredienteId getRecetaIngredienteId() {
        return recetaIngredienteId;
    }

    public Receta getReceta() {
        return receta;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

}
