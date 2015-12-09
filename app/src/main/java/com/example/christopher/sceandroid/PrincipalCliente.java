package com.example.christopher.sceandroid;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import logic.Cliente;

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
                .getColor(R.color.wallet_hint_foreground_holo_dark), 2));
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        // Return to previous page when we press back button
        if (this.mViewPager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.mViewPager.setCurrentItem(this.mViewPager.getCurrentItem() - 1);
    }
}