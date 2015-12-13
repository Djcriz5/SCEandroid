package com.example.christopher.sceandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.io.Serializable;

public class MenuPrincipalFragment extends Fragment implements Serializable {
    /**
     * Key to insert the background color into the mapping of a Bundle.
     */
    private static final long serialVersionUID = -1L;
    private static final String BACKGROUND_COLOR = "color";
    private static final String INDEX = "index";
    private static final String PRINCIPALP = "principalp";
    private int color;
    private int index;
    private ImageButton botonInfoCliente;
    private ImageButton botonInfoApp;
    private ImageButton botonQuejasyS;
    private ImageButton botonVentanaCompras;

    //catalogos
    public static MenuPrincipalFragment newInstance(int color, int index) {
        // Instantiate a new fragment
        MenuPrincipalFragment fragment = new MenuPrincipalFragment();
        // Save the parameters
        Bundle bundle = new Bundle();
        bundle.putInt(BACKGROUND_COLOR, color);
        bundle.putInt(INDEX, index);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu_principal, container, false);
        // Change te background color
        rootView.setBackgroundColor(this.color);
        botonInfoApp = (ImageButton) rootView.findViewById(R.id.InformacionAplicacion);
        botonInfoApp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                botonInfoApp.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));

            }
        });

        botonInfoCliente = (ImageButton) rootView.findViewById(R.id.InformacionCliente);
        botonInfoCliente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                botonInfoCliente.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));


            }
        });
        botonQuejasyS = (ImageButton) rootView.findViewById(R.id.QuejasySugerencias);
        botonQuejasyS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                botonQuejasyS.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));

            }
        });


        botonVentanaCompras = (ImageButton) rootView.findViewById(R.id.VentanaDeCompras);
        botonVentanaCompras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                botonVentanaCompras.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.alpha));
                CambiadorDePagina.cambiarPagina(2,AnimationUtils.loadAnimation(getActivity(), R.anim.alphaf));
            }
        });


        return rootView;

    }


}
