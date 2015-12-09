package logic;

import java.io.Serializable;

/**
 * Created by Christopher on 03/12/2015.
 */
public class Golosina extends Producto implements IContable, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -3729388314372959101L;
    private int indexGolosina;
    private String tipoGolosina;
    private double precio;
    private boolean disponibilidad;

    public Golosina() {
        this(0);
    }

    public Golosina(int id) {
        super(5);
        indexGolosina = id;
        tipoGolosina = setTipoDeGolosina(indexGolosina);
        precio = setPrecio(tipoGolosina);
        disponibilidad = setDisponibilidad();
    }

    public Golosina(String tipo) {
        super(5);
        indexGolosina = tipoGolosina(tipo);
        tipoGolosina = setTipoDeGolosina(indexGolosina);
        precio = setPrecio(tipoGolosina);
        disponibilidad = setDisponibilidad();
    }

    public void setGolosina(int id) {
        indexGolosina = id;
        tipoGolosina = setTipoDeGolosina(indexGolosina);
        precio = setPrecio(tipoGolosina);
        disponibilidad = setDisponibilidad();
    }


    public String setTipoDeGolosina(int id) {

        switch (id) {
            case 1:
                tipoGolosina = "Paleta";
                break;
            case 2:
                tipoGolosina = "Gomitas";
                break;
            case 3:
                tipoGolosina = "KitKat";
                break;
            case 4:
                tipoGolosina = "Pasitas";
                break;
            case 5:
                tipoGolosina = "gansito";
                break;
            case 6:
                tipoGolosina = "Carlos XV";
                break;
            case 7:
                tipoGolosina = "lunetas";
                break;

            default:
                indexGolosina = 0;
                tipoGolosina = "Golosina no especificada";
                break;
        }
        return tipoGolosina;
    }

    public double getPrecio() {
        return precio;
    }

    private boolean setDisponibilidad() {
        return (indexGolosina != 0);
    }

    public void setHelado(int idx) {
        indexGolosina = idx;
        tipoGolosina = setTipoDeGolosina(indexGolosina);
        precio = setPrecio(tipoGolosina);
        disponibilidad = setDisponibilidad();
    }

    public double setPrecio(String tipo) {
        switch (tipo) {
            case "Paleta":
                precio = 2.50;
                break;
            case "Gomitas":
                precio = 5.50;
                break;
            case "KitKat":
                precio = 8.50;
                break;
            case "Pasitas":
                precio = 6.00;
                break;
            case "gansito":
                precio = 10.0;
                break;
            case "Carlos XV":
                precio = 6.50;
                break;
            case "lunetas":
                precio = 7.00;
                break;
            default:
                precio = 0;
                break;
        }
        return precio;
    }

    private int tipoGolosina(String golosina) {
        int index = 0;
        switch (golosina) {
            case "Paleta":
                index = 1;
                break;
            case "Gomitas":
                index = 2;
                break;
            case "KitKat":
                index = 3;
                break;
            case "Pasitas":
                index = 4;
                break;
            case "gansito":
                index = 5;
                break;
            case "Carlos XV":
                index = 6;
                break;
            case "lunetas":
                index = 7;
                break;
            default:
                index = 0;
                break;
        }
        return index;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (disponibilidad) {
            builder.append("\n- " + tipoGolosina + "  Precio: $" + precio);
        } else {
            builder.append("Golosina No Disponible");
        }
        return builder.toString();

    }

}

