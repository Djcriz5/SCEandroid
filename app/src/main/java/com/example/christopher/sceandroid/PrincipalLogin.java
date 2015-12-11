package com.example.christopher.sceandroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import logic.Cliente;
import logic.ControladorBaseDeDatos;

/**
 * A login screen that offers login via email/password.
 */
public class PrincipalLogin extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */    //private static final int REQUEST_READ_CONTACTS = 0;

    private Cliente usuarioActual=null;
    private ControladorBaseDeDatos baseDeDatosControler;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);
        try {
            baseDeDatosControler=new ControladorBaseDeDatos(PrincipalLogin.this);
            crearDialogo("Se cargo correctamente la base de datos").show();
        } catch (Exception e) {
            crearDialogo("error al cargar base de datos").show();
        }
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        //populateAutoComplete();
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                attemptLogin();
            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        /*if (usuarioActual != null) {
            return;
        }*/
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();


        loginRegister(email, password, mEmailView);

    }

    //comprueba que sea un email valido
    private boolean isEmailValid(String email) {
        return email.length() > 2;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public void loginRegister(String nom, String pass, View focusView) {
        if (nom.length() < 3 || pass.length() < 3) {
            if (nom.length() < 3) {
                mEmailView.setError("Nombre demasiado corto escoja otro");
            }
            if (pass.length() < 3) {
                mPasswordView.setError("Password muy corta eliga otra");
            }
        } else {
            if (buscarPorNombre(nom) != null) {
                if (buscarPorNombre(nom).getPassword().equals(pass)) {
                    usuarioActual = buscarPorNombre(nom);
                    Intent i = new Intent(this, PrincipalCliente.class);
                    i.putExtra("Cliente", usuarioActual);
                    i.putExtra("ContextoDB",baseDeDatosControler.getContextdb().getDir("data", 0));
                    startActivity(i);
                } else {
                    mPasswordView.setError("Correo o Contraseña invalido");
                }
            } else {
                addCliente(nom, pass);
            }
        }
    }

    public void addCliente(String nom, String pass) {
        try {
            ControladorBaseDeDatos.almacenarEnBaseD(new Cliente(nom, pass));
            crearDialogo("Registro exitoso").show();
        } catch (Exception e) {
            crearDialogo("error al alamcenar cliente en base de datos").show();
        }
    }

    public Cliente buscarPorNombre(String nombreBuscado) {
        Cliente buscado = null;
        for (Cliente c : ControladorBaseDeDatos.consultarBaseDeDatos()) {
            if (c.getNombre().equals(nombreBuscado)) {
                buscado = c;
            }
        }
        return buscado;
    }
    public Dialog crearDialogo(String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informacion:")
                .setIcon(
                        getResources().getDrawable(R.drawable.check))
                .setMessage(mensaje)
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        return builder.create();
    }
}

