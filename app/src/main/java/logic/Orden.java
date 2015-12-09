package logic;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
/**
 * Created by Christopher on 03/12/2015.
 */
public class Orden  implements Serializable {
    private static final long serialVersionUID = -2657784882927859057L;
    private LinkedList<Producto> lista;
    private String               idOrden;
    private double               precio;
    private boolean              confirmacion;
    private String               cheque;
    private int                  numeroDeProductos;
    public Orden(LinkedList<Producto> L) {
        lista = L;
        setIdOrden(getId());
        precio = setPrecio(lista);
        cheque = getCheque(lista);
        confirmacion = setConfirmacion();
        numeroDeProductos = getNumeroDeProductos();

    }

    public Orden() {
        lista = new LinkedList<Producto>();
        setIdOrden(getId());
        precio = setPrecio(lista);
        cheque = getCheque(lista);
        confirmacion = setConfirmacion();
    }

    public double getPrecio() {
        return precio;
    }

    public double setPrecio(LinkedList<Producto> list) {
        IContable p;
        Iterator<Producto> it;
        it = list.iterator();
        while (it.hasNext()) {
            p = (IContable) it.next();
            precio += p.getPrecio();

        }
        return precio;
    }

    public String getCheque(LinkedList<Producto> list) {
        IContable p;
        Iterator<Producto> it;
        it = list.iterator();
        while (it.hasNext()) {
            p = (IContable) it.next();
            cheque += p.toString();

        }
        return cheque;
    }

    public void addProductos(Producto e) {
        precio = 0;
        cheque = "";
        lista.add(e);
        precio = setPrecio(lista);
        cheque = getCheque(lista);
    }

    public void removeProductos(Producto o) {
        precio = 0;
        cheque = "";
        lista.remove(o);
        precio = setPrecio(lista);
        cheque = getCheque(lista);
    }

    public boolean setConfirmacion() {
        return (lista != null);
    }

    public int getNumeroDeProductos() {

        numeroDeProductos = lista.size();
        return numeroDeProductos;
    }

    private String getId() {
        int longitud = (int) (Math.random() * 10) + 1;
        ;
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (confirmacion) {
            builder.append("\nSistema de Cafeteria Escom");
            builder.append("\nIdentificacion de Orden:" + idOrden);
            builder.append("\nCheque:" + cheque);
            builder.append("\nNumero de Productos:" + numeroDeProductos);
            builder.append("\nel precio de la orden es de : $" + precio);
        } else {
            builder.append("Orden  errorea intente otra ves");
        }
        return builder.toString();

    }

    public void setIdOrden(String id) {
        idOrden = id;
    }

    public String getIdOrden() {
        return idOrden;
    }

}
