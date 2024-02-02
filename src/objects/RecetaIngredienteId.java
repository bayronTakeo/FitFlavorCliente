/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author bayro
 */
public class RecetaIngredienteId {

    private int receta;

    private int ingrediente;

    public RecetaIngredienteId(int receta, int ingrediente) {
        this.receta = receta;
        this.ingrediente = ingrediente;
    }

    public RecetaIngredienteId() {
    }

    public int getReceta() {
        return receta;
    }

    public int getIngrediente() {
        return ingrediente;
    }

    public void setReceta(int receta) {
        this.receta = receta;
    }

    public void setIngrediente(int ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.receta;
        hash = 67 * hash + this.ingrediente;
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
        final RecetaIngredienteId other = (RecetaIngredienteId) obj;
        if (this.receta != other.receta) {
            return false;
        }
        if (this.ingrediente != other.ingrediente) {
            return false;
        }
        return true;
    }
}
