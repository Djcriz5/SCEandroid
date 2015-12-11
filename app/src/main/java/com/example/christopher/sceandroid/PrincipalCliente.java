package com.example.christopher.sceandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import logic.Cliente;
import logic.ControladorBaseDeDatos;

public class PrincipalCliente extends AppCompatActivity {
    private ViewPager mViewPager;
    private Cliente usuarioActual;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_cliente);
        try {
            Bundle b = getIntent().getExtras();
            usuarioActual = (Cliente) b.get("Cliente");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        AdaptadorDEPagina adaptadorPagina = new AdaptadorDEPagina(getSupportFragmentManager());
        adaptadorPagina.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.colorPrimaryDark), 0));
        adaptadorPagina.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.colorPrimary), 1));
        adaptadorPagina.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.colorAccent), 2));
        adaptadorPagina.addFragment(VentanaDeCompras.newInstance(Color.DKGRAY, 3, usuarioActual));
        adaptadorPagina.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.colorPrimaryDark), 4));
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(adaptadorPagina);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, usuarioActual.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal_cliente, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
           /* case R.id.action_search:de
                showSnackBar("Comenzar a buscar...");
                return true;*/
            case R.id.action_settings:
                dialogoSalir().show();
                //crearDialogo("Se abren los ajustes").show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Dialog crearDialogo(String mensaje) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Se a comprado el producto:")
                .setIcon(
                        getResources().getDrawable(R.drawable.dialogicon))
                .setMessage(mensaje)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        return builder.create();
    }
    public Dialog dialogoSalir() {
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(this);
        builder.setMessage("¿Realmente Deseas Salir ?")
                .setTitle("Exit")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(PrincipalCliente.this, PrincipalLogin.class);
                        startActivity(i);
                        ControladorBaseDeDatos.almacenarEnBaseD(usuarioActual);
                        ControladorBaseDeDatos.cerrarConexion();
                        PrincipalCliente.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }



    public void onBackPressed() {
        // Return to previous page when we press back button
        if (this.mViewPager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem() - 1);
    }
}