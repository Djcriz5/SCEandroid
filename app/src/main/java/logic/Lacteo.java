package logic;

import java.io.Serializable;

/**
 * Created by Christopher on 03/12/2015.
 */
public class Lacteo extends Producto implements IContable ,Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8102432301259462664L;
    private int     indexLacteo;
    private String  tipoLacteo;
    private double  precio;
    private boolean disponibilidad;

    public Lacteo() {
        this(0);
    }

    public Lacteo(int index) {
        super(3);
        indexLacteo = index;
        tipoLacteo = setTipoDeLacteos(indexLacteo);
        precio=setPrecio(tipoLacteo);
        disponibilidad=setDisponibilidad();

    }

    public String setTipoDeLacteos(int id) {

        switch (id) {
            case 1:
                tipoLacteo = "Griego Yoplait";
                break;
            case 2:
                tipoLacteo = "Yoplait de fresa";
                break;
            case 3:
                tipoLacteo = "Yoplait de Manzana";
                break;
            case 4:
                tipoLacteo = "Leche 400ml Santa clara";
                break;
            case 5:
                tipoLacteo = "Leche Deslactosada 400ml Santa clara";
                break;
            case 6:
                tipoLacteo = "Jugo Ades";
                break;
            default:
                indexLacteo = 0;
                tipoLacteo = "Lacteo no especificado";
                break;
        }
        return tipoLacteo;
    }

    public double getPrecio() {
        return precio;
    }
    private boolean setDisponibilidad(){
        return(indexLacteo!=0);
    }
    public void setLacteo(int idx){
        indexLacteo = idx;
        tipoLacteo = setTipoDeLacteos(indexLacteo);
        precio=setPrecio(tipoLacteo);
        disponibilidad=setDisponibilidad();
    }
    public double setPrecio(String tipo) {
        switch (tipo) {
            case "Griego Yoplait":
                precio = 4.50;
                break;
            case "Yoplait de fresa":
                precio = 3.50;
                break;
            case "Yoplait de Manzana":
                precio = 3.50;
                break;
            case "Leche 400ml Santa clara":
                precio = 7.50;
                break;
            case "Leche Deslactosada 400ml Santa clara":
                precio = 6.50;
                break;
            case "Jugo Ades":
                precio = 6.50;
                break;
            default:
                precio = 0;
                break;
        }
        return precio;
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (disponibilidad) {
            builder.append( "\n- " + tipoLacteo + " Precio: $" + precio );
        } else {
            builder.append("lacteo No Disponible");
        }
        return builder.toString();

    }
}
