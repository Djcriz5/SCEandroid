package logic;

import java.io.Serializable;

/**
 * Created by Christopher on 03/12/2015.
 */
public abstract class Producto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2874196855991618490L;
    private int               index;
    private String            tipoDeProducto;

    public Producto(int id) {
        setIndex(id);
        tipoDeProducto = setTipoDeProducto(getIndex());
    }

    public String getTipoDeProducto() {
        return tipoDeProducto;
    }

    public String setTipoDeProducto(int idx) {
        switch (idx) {
            case 1:
                tipoDeProducto = "Frituras";
                break;
            case 2:
                tipoDeProducto = "Bebidas";
                break;
            case 3:
                tipoDeProducto = "Lacteos";
                break;
            case 4:
                tipoDeProducto = "Helados";
                break;
            case 5:
                tipoDeProducto = "Golosinas";
                break;
            case 6:
                tipoDeProducto = "Preparado";
                break;
            case 7:
                tipoDeProducto = "Cafe";
                break;
            case 8:
                tipoDeProducto = "Paquete del dia";
                break;

            default:
                tipoDeProducto = "Preparado";
                break;
        }
        return tipoDeProducto;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\ntipo de producto:\n" + tipoDeProducto);
        return builder.toString();

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
