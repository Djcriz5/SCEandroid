package logic;

import android.content.Context;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.Serializable;
import java.util.ArrayList;

public  class ControladorBaseDeDatos implements Serializable{
    private static final long serialVersionUID = -5759337342200894458L;
    private static ObjectContainer baseDeDatos;
    private static Context context;
   private static ArrayList<Cliente> listaCliente=new ArrayList<>();
    public ControladorBaseDeDatos (Context context){
        ControladorBaseDeDatos.context =context;
        baseDeDatos= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                db4oDBFullPath(ControladorBaseDeDatos.context));
    }
    public ControladorBaseDeDatos (String ct,ArrayList<Cliente> listaCliente){
        ControladorBaseDeDatos.listaCliente =listaCliente;
        baseDeDatos= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                db4oDBFullPath(ct));
    }
// para obtener el arryalist
    public static ArrayList<Cliente> consultarBaseDeDatos() {
        ObjectSet resultado = baseDeDatos.queryByExample(listaCliente);
        listaCliente=(ArrayList<Cliente>) resultado.get(0);
        return (ArrayList<Cliente>) resultado.get(0);
    }
    public static void almacenarEnBaseD(Cliente c) {
        try {
            listaCliente.add(c);
            baseDeDatos.store(listaCliente);
            baseDeDatos.commit();
            System.out.println("Se ha almacenado correctamente en la base de datos");
        } catch (Exception e) {
            System.out.println("Se ha porducido un error en la insercion");
        }
    }
    public static void cerrarConexion() {
        try {
            baseDeDatos.close();
        } catch (Exception e) {
            System.out.println("error al cerrar la conexion");
        }
    }

    public ObjectContainer getBaseDeDatos() {
        return baseDeDatos;
    }
    private String db4oDBFullPath(Context ctx) {
        return ctx.getDir("data", 0) + "/" + "clientes.db4o";
    }
    private String db4oDBFullPath(String  ctx) {
        return ctx + "/" + "clientes.db4o";
    }

    public void setBaseDeDatos(ObjectContainer baseDeDatos) {
        ControladorBaseDeDatos.baseDeDatos = baseDeDatos;
    }

    public Context getContextdb(){
        return  context;
    }
}
