package objects;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Esta clase contiene toda la informacion del cliente
 *
 * @author Bayron
 */
@XmlRootElement(name = "cliente")
public class Cliente extends Usuario {

    private static final long serialVersionUID = 1L;

    private SimpleObjectProperty<EnumSexo> sexo;
    private SimpleFloatProperty peso;
    private SimpleObjectProperty<EnumObjetivo> objetivo;
    private SimpleStringProperty altura;
    private List<Receta> recetasUsu;

    public Cliente(EnumSexo sexo, float peso, EnumObjetivo objetivo, String altura, List<Receta> recetasUsu, Integer user_id, String email, String nombreCompleto, Date fechaNacimiento, String telefono, String direccion, String codigoPostal, String contrasenia, EnumPrivilegios privilegio) {
        super(user_id, email, nombreCompleto, fechaNacimiento, telefono, direccion, codigoPostal, contrasenia, privilegio);
        this.sexo = new SimpleObjectProperty<>(sexo);
        this.peso = new SimpleFloatProperty(peso);
        this.objetivo = new SimpleObjectProperty<>(objetivo);
        this.altura = new SimpleStringProperty(altura);
        this.recetasUsu = recetasUsu;
    }

    // Constructor por defecto sin argumentos
    public Cliente() {
        super(0, "usuario@gmail.com", "usuario", null, "123", "ejemplo", "123", "abcd*1234", EnumPrivilegios.USUARIO);
        this.sexo = new SimpleObjectProperty<>(EnumSexo.HOMBRE);
        this.peso = new SimpleFloatProperty(33);
        this.objetivo = new SimpleObjectProperty<>(EnumObjetivo.MANTENERSE);
        this.altura = new SimpleStringProperty("174");
    }

    // Setters
    public void setSexo(EnumSexo sexo) {
        this.sexo.set(sexo);
    }

    public void setRecetasUsu(List<Receta> recetasUsu) {
        this.recetasUsu = recetasUsu;
    }

    public void setPeso(float peso) {
        this.peso.set(peso);
    }

    public void setObjetivo(EnumObjetivo objetivo) {
        this.objetivo.set(objetivo);
    }

    public void setAltura(String altura) {
        this.altura.set(altura);
    }

    // Getters
    public List<Receta> getRecetasUsu() {
        return recetasUsu;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public EnumSexo getSexo() {
        return sexo.get();
    }

    public float getPeso() {
        return peso.get();
    }

    public EnumObjetivo getObjetivo() {
        return objetivo.get();
    }

    public String getAltura() {
        return altura.get();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (super.getUser_id() != null ? getUser_id().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((super.getUser_id() == null && other.getUser_id() != null) || (super.getUser_id() != null && !super.getUser_id().equals(other.getUser_id()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
