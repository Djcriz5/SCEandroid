package logic;

import java.io.Serializable;

public class Comunicador implements Serializable {
    private static final long serialVersionUID = -137593360894458L;
    private Object contenido;
    public Comunicador(Object o){
        contenido=o;
    }
    public Object getContenido(){
        return  contenido;
    }
    public void setContenido(Object o){
        contenido=0;
    }
}
