package logic;

import java.io.Serializable;

/**
 * Created by Christopher on 03/12/2015.
 */
public class IngredienteEspecial implements Serializable {

    private static final long serialVersionUID = -6622215012398760316L;
    double precioIngrediente;
    boolean disponibilidad;
    private int index;
    private String tipo;

    public IngredienteEspecial() {
        this(0);
    }

    public IngredienteEspecial(int cual) {
        setIndex(cual);
        setTipo(index);
        precioIngrediente = setPrecio(getTipo());
        disponibilidad = setDisponibilidad();

    }

    private String setTipo(int indexP) {
        switch (indexP) {
            case 1:
                tipo = "jamon extra";
                break;
            case 2:
                tipo = "Pechuga de pavo";
                break;
            case 3:
                tipo = "queso extra";
                break;
            case 4:
                tipo = "Aguacate";
                break;
            case 5:
                tipo = "Aderezos";
                break;
            case 6:
                tipo = "Guacamole";
                break;
            case 7:
                tipo = "BBq";
                break;
            case 8:
                tipo = "Wasabi";
                break;
            case 0:
                tipo = "no ingrediente extra";

                break;
        }
        return tipo;
    }

    private double setPrecio(String cual) {
        switch (cual) {
            case "jamon extra":
                precioIngrediente = 7.50;

                break;
            case "Pechuga de pavo":
                precioIngrediente = 9.50;
                break;
            case "queso extra":
                precioIngrediente = 7.50;
                break;
            case "Aguacate":
                precioIngrediente = 5.00;
                break;
            case "Aderezos":
                precioIngrediente = 3.75;
                break;
            case "Guacamole":
                precioIngrediente = 3.25;
                break;
            case "BBq":
                precioIngrediente = 4.55;
                break;
            case "Wasabi":
                precioIngrediente = 5.55;
            default:
                precioIngrediente = 0;
                break;
        }
        return precioIngrediente;
    }

    private boolean setDisponibilidad() {
        return (getIndex() != 0);
    }

    public double getPrecio() {
        return precioIngrediente;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int cual) {
        index = cual;
        setTipo(cual);
    }

    public String getTipo() {
        return tipo;
    }

}
