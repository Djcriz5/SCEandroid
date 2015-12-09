package com.example.christopher.sceandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import logic.Bebida;
import logic.Cliente;
import logic.Golosina;
import logic.Helado;
import logic.Lacteo;
import logic.PaqueteDelDia;
import logic.Preparado;

public class VentanaDeCompras extends Fragment {

    /**
     * Key to insert the background color into the mapping of a Bundle.
     */
    private static final String BACKGROUND_COLOR = "color";

    /**
     * Key to insert the index page into the mapping of a Bundle.
     */
    private static final String INDEX = "index";
    private static final String USUARIO = "usuarioActual";

    private int color;
    private int index;
    private ImageButton heladob;
    private ImageButton lacteob;
    private ImageButton bebidab;
    private ImageButton golosinasb;
    private ImageButton preparadoB;
    private ImageButton confirmarPedido;
    private ImageButton paqueteB;
    private Cliente usuarioActual = new Cliente();

    //catalogos
    private String[] catalogoDulces = {"Paleta $2.50", "Gomitas $5.50", "KitKat $8.50", "Pasitas $6.0", "gansito $10.0", "Carlos XV $6.5", "lunetas $7.0"};
    private String[] catalogoBebidas = {"coca cola $9.50", "Sprite $7.50", "Nestea $10.0", "Manzanita $7.50", "Fanta $8.50", "Boing Guayaba $7.50", "Boing uva $7.50", "Boing manzana $7.50", "Agua embotellada $6.50"};
    private String[] catalogoLacteos = {"Griego Yoplait $4.50", "Yoplait de fresa $3.50", "Yoplait de Manzana $3.50", "Leche 400ml Santa clara $7.50", "Leche Deslactosada 400ml Santa clara $6.50", "Jugo Ades $6.50"};
    private String[] catalogoHelados = {"Helado de Yogurt $17.50", "Helado fresa $15.50", "Helado de Chocolate $12.50", "Paleta magnum Almendras $20.50", "Helado Vainilla  10.50", "Helado de yogurt Taro $18.50"};
    private String[] catalogoPreparados = {"Sandwich $14.50", "Molletes $12.50", "Tortas $17.50", "Ensalada de Atun $18.50", "Ensalada de pollo $21.0", "Pechuga Empanisada con ensalada $22.0", "Enchiladas $18.50", "Orden de tacos $14.50", "Guisado Del dia $11.0", "Sushi $15.0"};
    private String[] catalogoIExtra = {"jamon extra $7.50", "Pechuga de pavo $9.50", "queso extra $7.50", "Aguacate  $5.0", "Aderezos $3.75", "Guacamole $3.25", "Salsa BBq $4.55", "Wasabi $5.55"};


    public static VentanaDeCompras newInstance(int color, int index, Cliente cliente) {

        // Instantiate a new fragment
        VentanaDeCompras fragment = new VentanaDeCompras();

        // Save the parameters
        Bundle bundle = new Bundle();
        bundle.putInt(BACKGROUND_COLOR, color);
        bundle.putInt(INDEX, index);
        bundle.putSerializable(USUARIO, cliente);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Carga los atributos desde los parametros del bundle, si estos no existen en
        // el bundle entonces ejecuta uno definido
        // por default ejemplo no existe entonces : carga este objeto
        this.color = (getArguments() != null) ? getArguments().getInt(
                BACKGROUND_COLOR) : Color.GRAY;
        this.index = (getArguments() != null) ? getArguments().getInt(INDEX)
                : -1;
        this.usuarioActual = (getArguments() != null) ? (Cliente) getArguments().getSerializable(USUARIO) : new Cliente();
    }

    public void setCliente(Cliente c) {
        usuarioActual = new Cliente();
        usuarioActual = c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_ventana_de_compras, container, false);
        // Change te background color
        rootView.setBackgroundColor(this.color);
        golosinasb = (ImageButton) rootView.findViewById(R.id.botonGolosina);
        golosinasb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearDialogoGolosina().show();
            }
        });
        bebidab = (ImageButton) rootView.findViewById(R.id.botonBebida);
        bebidab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                crearDialogoBebida().show();
            }
        });
        lacteob = (ImageButton) rootView.findViewById(R.id.botonLacteo);
        lacteob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                crearDialogoLacteo().show();
            }
        });

        heladob = (ImageButton) rootView.findViewById(R.id.botonHelado);
        heladob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                heladob.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));

                crearDialogoHelado().show();
            }
        });
        preparadoB = (ImageButton) rootView.findViewById(R.id.botonPreparado);
        preparadoB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                crearDialogoPreparado().show();
            }
        });
        paqueteB = (ImageButton) rootView.findViewById(R.id.botonPaquete);
        paqueteB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                activarBebidaExtra().show();
            }
        });

        confirmarPedido = (ImageButton) rootView.findViewById(R.id.botonConfirmar);
        confirmarPedido.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                confirmarCompra().show();
            }
        });



        return rootView;

    }


    public Dialog crearDialogo(String mensaje) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Se a comprado el producto:")
                .setIcon(
                        getResources().getDrawable(R.drawable.check))
                .setMessage(mensaje)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        return builder.create();
    }

    public Dialog crearDialogoGolosina() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona una golosina...").setItems(
                catalogoDulces, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        usuarioActual.comprar(new Golosina(which + 1));
                        crearDialogo(new Golosina(which + 1).toString()).show();
                    }
                }).setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        return builder.create();
    }

    public Dialog crearDialogoBebida() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona una bebida...").setItems(
                catalogoBebidas, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        usuarioActual.comprar(new Bebida(which + 1));
                        crearDialogo(new Bebida(which + 1).toString()).show();
                    }
                }).setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        return builder.create();
    }

    public Dialog crearDialogoLacteo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona un lacteo...").setItems(
                catalogoLacteos, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        usuarioActual.comprar(new Lacteo(which + 1));
                        crearDialogo(new Lacteo(which + 1).toString()).show();
                    }
                }).setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        return builder.create();
    }

    public Dialog crearDialogoHelado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona un helado...").setItems(
                catalogoHelados, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        usuarioActual.comprar(new Helado(which + 1));
                        crearDialogo(new Helado(which + 1).toString()).show();
                    }
                }).setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        return builder.create();
    }

    public Dialog crearDialogoPreparado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona un Platillo...").setItems(
                catalogoPreparados, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        activarIExtra(which + 1).show();
                    }
                }).setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        return builder.create();
    }


    public Dialog activarBebidaExtra() {
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setMessage("¿Deseas elegir la bebida?")
                .setTitle("Eleccion de bebida")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        crearDialogoBebidaPaquete().show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        usuarioActual.comprar(new PaqueteDelDia());
                        crearDialogo(new PaqueteDelDia().toString()).show();
                    }
                });

        return builder.create();

    }

    public Dialog activarIExtra(int indexPreparado) {
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        final int indexP = indexPreparado;
        builder.setMessage("¿Desea Agregar Ingrediente Especial?")
                .setTitle("Ingrediente Especial")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        crearDialogoIExtra(indexP).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        usuarioActual.comprar(new Preparado(indexP));
                        crearDialogo(new Preparado(indexP).toString()).show();
                        dialog.cancel();
                    }
                });

        return builder.create();

    }

    public Dialog crearDialogoIExtra(int idx) {
        final int indexP = idx;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona un Ingrediente extra...").setItems(
                catalogoIExtra, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        usuarioActual.comprar(new Preparado(indexP, which + 1));
                        crearDialogo(new Preparado(indexP, which + 1).toString()).show();
                    }
                }).setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        return builder.create();
    }

    public Dialog crearDialogoBebidaPaquete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona un Bebida...").setItems(
                catalogoBebidas, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        usuarioActual.comprar(new PaqueteDelDia(which+1));
                        crearDialogo(new PaqueteDelDia(which+1).toString()).show();
                    }
                }).setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        return builder.create();
    }



    public Dialog confirmarCompra() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Confirma la compra:\n" + usuarioActual.toString())
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });

        return builder.create();
    }
}
