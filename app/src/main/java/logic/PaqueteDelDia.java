package logic;

import java.io.Serializable;

/**
 * Created by Christopher on 03/12/2015.
 */
public class PaqueteDelDia extends Producto implements IContable,Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1201998190548038058L;
    private Preparado platofuerte;
    private String[] guarniciones={"Arroz","Sopa","spaguetti","frijoles"};
    private Bebida    bebidaPaquete;
    private String    guarnicion;
    private boolean   disponibilidad;
    private double    precio;

    public PaqueteDelDia() {
        super(8);
        platofuerte = new Preparado(9);
        bebidaPaquete=new Bebida((int) (Math.random() *9)+1);
        guarnicion=setGuarnicion();
        precio=(platofuerte.getPrecio()+bebidaPaquete.getPrecio()+5);
        disponibilidad=setDisponibilidad();


    }

    public PaqueteDelDia(int bebida) {//el precio de este sera el costo del plato del dia + 5 guarnicion+ precio de la bebida
        super(8);
        platofuerte = new Preparado(9);
        bebidaPaquete = new Bebida(bebida);
        guarnicion=setGuarnicion();
        precio=(platofuerte.getPrecio()+bebidaPaquete.getPrecio()+5);
        disponibilidad=setDisponibilidad();

    }
    public PaqueteDelDia(int bebida,int guanicion) {//el precio de este sera el costo del plato del dia + 5 guarnicion+ precio de la bebida
        super(8);
        platofuerte = new Preparado(9);
        bebidaPaquete = new Bebida(bebida);
        guarnicion=guarniciones[guanicion];
        precio=(platofuerte.getPrecio()+bebidaPaquete.getPrecio()+5);
        disponibilidad=setDisponibilidad();

    }

    public Preparado getPlatofuerte() {
        return platofuerte;
    }

    public Bebida getBebidaPaquete() {
        return bebidaPaquete;
    }

    public void setBebidaPaquete(int idx) {
        bebidaPaquete.setBebida(idx);
    }

    public String setGuarnicion() {
        int seleccion = (int) (Math.random() * 4);
        guarnicion=guarniciones[seleccion];
        seleccion=0;
        return guarnicion;
    }
    public void setGuarnicion(int cual){
        guarnicion=guarniciones[cual];
    }

    public String getGuarnicion() {
        return guarnicion;
    }

    public double getPrecio() {
        return precio;
    }

    private boolean setDisponibilidad(){
        return(platofuerte.getIndex()!=0);
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (disponibilidad) {
            builder.append(
                    super.toString() + "\nPlato fuerte:" + platofuerte +"\nGuarnicion:"+guarnicion+"\nBebida: "+bebidaPaquete+"\nPrecio paquete: $" + precio + "\nDisponible" );
        } else {
            builder.append("paquete No Disponible");
        }
        return builder.toString();

    }

}
