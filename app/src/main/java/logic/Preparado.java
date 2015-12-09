package logic;

import java.io.Serializable;

/**
 * Created by Christopher on 03/12/2015.
 */
public class Preparado extends Producto implements IContable,Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8314460045236646718L;
    private IngredienteEspecial extra;
    private int                 indexPreparado;
    private String              tipoDePreparado;
    private double              precio;
    private boolean             disponibilidad;
    private int                 FlagGuisadoDeldia;

    public Preparado(int id) {
        super(6);
        extra = new IngredienteEspecial();
        FlagGuisadoDeldia = 0;
        indexPreparado = id;
        tipoDePreparado = setTipoDePreparado(indexPreparado);
        if (indexPreparado == 9) {
            getGuisadoDeldia();
            FlagGuisadoDeldia++;
            tipoDePreparado = setTipoDePreparado(indexPreparado);

        }
        precio = setPrecio(tipoDePreparado);
        disponibilidad=setDisponibilidad();
    }

    public Preparado(int id, int extraID) {
        super(6);
        extra = new IngredienteEspecial(extraID);
        FlagGuisadoDeldia = 0;
        indexPreparado = id;
        tipoDePreparado = setTipoDePreparado(indexPreparado);
        if (indexPreparado == 9) {
            getGuisadoDeldia();
            FlagGuisadoDeldia++;
            tipoDePreparado = setTipoDePreparado(indexPreparado);

        }
        precio = setPrecio(tipoDePreparado) + extra.getPrecio();
    }

    public String setTipoDePreparado(int idx) {
        switch (idx) {
            case 1:
                tipoDePreparado = "Sandwich";
                break;
            case 2:
                tipoDePreparado = "Tortas";
                break;
            case 3:
                tipoDePreparado = "Molletes";
                break;
            case 4:
                tipoDePreparado = "Ensalada de Atun";
                break;
            case 5:
                tipoDePreparado = "Ensalada de pollo";
                break;
            case 6:
                tipoDePreparado = "Pechuga Empanisada con ensalada";
                break;
            case 7:
                tipoDePreparado = "Enchiladas";
                break;
            case 8:
                tipoDePreparado = "Orden de tacos";
                break;
            case 9:
                tipoDePreparado = "Guisado Del dia";
                break;
            case 10:
                tipoDePreparado = "Sushi";
                break;
            default:
                indexPreparado = 0;
                tipoDePreparado = " no especificado";
                break;
        }
        return tipoDePreparado;
    }

    public double setPrecio(String tipo) {
        switch (tipo) {
            case "Sandwich":
                precio = 14.50;
                break;
            case "Molletes":
                precio = 12.50;

                break;
            case "Tortas":
                precio = 17.50;

                break;
            case "Ensalada de Atun":
                precio = 18.50;

                break;
            case "Ensalada de pollo":
                precio = 21.00;
                break;
            case "Pechuga Empanisada con ensalada":
                precio = 22.00;
                break;
            case "Enchiladas":
                precio = 18.50;
                break;
            case "Orden de tacos":
                precio = 14.50;
                break;
            case "Guisado Del dia":
                precio = 11.00;
                break;
            case "Sushi":
                precio = 15.00;
                break;
            default:
                indexPreparado = 0;
                tipoDePreparado = " no especificado";
                break;
        }
        return precio;

    }

    public void getGuisadoDeldia() {
        indexPreparado = (int) (Math.random() * (9 - 2) + 2);
    }

    public void setIngredienteEspecial(int cual) {
        IngredienteEspecial p = new IngredienteEspecial(cual);
        extra = p;
        precio += extra.precioIngrediente;
        p=null;
    }
    private boolean setDisponibilidad(){
        return(indexPreparado!=0);
    }
    public double getPrecio() {
        return precio;
    }
    public int getIndex(){
        return indexPreparado;
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (FlagGuisadoDeldia != 0) {
            builder.append("\n - Guisado del dia:\n" + tipoDePreparado);
        } else
            builder.append("\n- " + tipoDePreparado);
        if (extra.getIndex() != 0) {
            builder.append("\ningredientes especiales:\n" + extra.getTipo());
        }
        builder.append("\nPrecio: $" + precio);
        return builder.toString();

    }
}
