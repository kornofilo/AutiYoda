package com.rialvik.autiyoda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;

public class PasswordResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Verificamos el tema seleccionado por el usuario
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean tema = pref.getBoolean("nightMode_switch", false);
        //Dependiendo del valor recuperado, se establece el tema para la activity.
        if(tema) {
            setTheme(R.style.DarkTheme);

        }

        setContentView(R.layout.activity_password_reset);

        Button passwrdResetButton = findViewById(R.id.reset_password_button);
        passwrdResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailEditText = findViewById(R.id.EditText_Email);
                String email = emailEditText.getText().toString();
                if(!email.equals("")){
                    if(isEmailValid(email)){
                        passwordReset(emailEditText.getText().toString());
                    }else
                        emailEditText.setError(getString(R.string.error_invalid_email));

                }else
                    emailEditText.setError(getString(R.string.error_field_required));
            }
        });
    }



    private void passwordReset(String email){
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            notifyUser(getString(R.string.success),getString(R.string.password_reset_succesful));
                        else {
                            try {
                                throw task.getException();
                            } catch (FirebaseNetworkException e) {
                                //Si no existe conexión a internet...
                                notifyUser(getString(R.string.error), getString(R.string.error_no_connection));
                            } catch (Exception e) {
                                notifyUser(getString(R.string.error), getString(R.string.error_user_not_found));
                            }
                        }
                    }
                });
    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public void notifyUser(String title, String message){
        //Creación del Alert Dialog que le indica al usuario que su cuenta ha sido creada exitosamente.
        AlertDialog.Builder builder = new AlertDialog.Builder(PasswordResetActivity.this);
        builder.setMessage(message)
                .setTitle(title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialogSuccessMSG = builder.create();
        dialogSuccessMSG.show();
    }
}
