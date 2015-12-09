package logic;

/**
 * Created by Christopher on 03/12/2015.
 */
import java.io.Serializable;
import java.util.ArrayList;



public class Cliente implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1175933602200894458L;
    private String            nombre;
    private long              numeroTargeta;
    private String            password;
    private Orden             pedido;
    private double            credito;

    public Cliente(String name, String pass, long num, double saldo) {
        nombre = name;
        password = pass;
        numeroTargeta = num;
        credito = saldo;
        pedido = new Orden();
    }

    public Cliente() {
        this("", "", 0, 800);
    }

    public Cliente(String nombre, String pass) {
        this(nombre, pass, 0, 800);
    }

    public Producto getProducto(String cual, int idx) {
        Producto p = null;
        switch (cual) {
            case "Bebida":
                p = new Bebida(idx);
                break;
            case "Golosina":
                p = new Golosina(idx);
                break;
            case "Helado":
                p = new Helado(idx);
                break;
            case "Lacteo":
                p = new Lacteo(idx);
                break;
            case "Paquete del dia":
                if (idx == 0) {
                    p = new PaqueteDelDia();
                } else {
                    p = new PaqueteDelDia(idx);
                }
                break;
            case "Preparado":
                p = new Preparado(idx);
                break;
            default:
                break;
        }
        return p;
    }

    public Producto getProducto(String cual, int idx, int es) {
        Producto p = null;
        switch (cual) {
            case "Paquete del dia":
                if (idx == 0) {
                    p = new PaqueteDelDia();
                } else if (es == 0) {
                    p = new PaqueteDelDia(idx);
                } else {
                    p = new PaqueteDelDia(idx, es);
                }
                break;
            case "Preparado":
                if (es == 0) {
                    p = new Preparado(idx);
                } else {
                    p = new Preparado(idx, es);
                }
                break;
            default:
                System.out.println("lo sentimos esos productos no se pueden personalizar");
                break;
        }
        return p;
    }

    public void comprar(Producto e) {
        if (numeroTargeta != 0) {
            if (credito > 0) {
                pedido.addProductos(e);
                credito -= pedido.getPrecio();
            } else {
                System.out.println("lo sentimos credito excedido");
            }
        } else {
            pedido.addProductos(e);
        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        nombre = name;
    }

    public long getNumeroTargeta() {
        return numeroTargeta;
    }

    public void setNumeroTargeta(long num) {
        numeroTargeta = num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public Orden getPedido() {
        return pedido;
    }

    public Orden setPedido(Orden pedido) {
        this.pedido = pedido;
        return this.pedido;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (numeroTargeta != 0) {
            builder.append("\nCliente: " + nombre).append("\nNumero de Targeta: " + numeroTargeta)
                    .append("\nCredito restante: " + credito);
        } else {
            builder.append("\nCliente : " + nombre);
        }
        if (pedido.getNumeroDeProductos() > 0) {
            builder.append("\n" + pedido);
        }
        return builder.toString();
    }

}
