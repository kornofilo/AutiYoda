package com.rialvik.autiyoda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SettingsFragment extends PreferenceFragment {
    private FirebaseAuth mAuth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        //Instanciación de Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        //setOnClickListener para las opciones de Logout y Eliminar cuenta.
        Preference prefLogout, prefDeleteAccount, prefNightMode;

        prefNightMode = findPreference("nightMode_switch");

        prefNightMode.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intentLogout = new Intent(getActivity(), PictogramsMenuActivity.class);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLogout);
                return true;
            }
        });

        prefLogout = findPreference("logout_pref");
        prefDeleteAccount = findPreference("delete_account_pref");
        prefLogout.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference pref) {
                FirebaseAuth.getInstance().signOut();
                Intent intentLogout = new Intent(getActivity(), LoginActivity.class);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLogout);
                return true;
            }
        });


        prefDeleteAccount.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference pref) {
                notifyUserDeleteAccount(getString(R.string.delete_account_warning));

                return true;
            }
        });//Fin onClick
    }

    public void deleteAccount(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intentLogout = new Intent(getActivity(), LoginActivity.class);
                            intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentLogout);
                        }
                    }
                });
    }

    public void notifyUserDeleteAccount(String message){
        //Creación del Alert Dialog que le indica al usuario que su cuenta ha sido creada exitosamente.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View inflator = inflater.inflate(R.layout.ask_password, null);
        builder.setView(inflator);
        builder.setMessage(message)
                .setTitle(R.string.warning);
        final EditText passwordET = inflator.findViewById(R.id.EditText_Password);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (passwordET.getText().toString().equals("")){
                    passwordET.setError(getString(R.string.error_invalid_password));
                }else {
                    login(passwordET.getText().toString());
                }

            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialogSuccesMSG = builder.create();
        dialogSuccesMSG.show();
    }

    public void login(String password){

        mAuth.signInWithEmailAndPassword(getCurrentUserEmail(), password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            deleteAccount();
                        }else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidCredentialsException | FirebaseAuthInvalidUserException e) {
                                //Si el usuario no existe...
                                if(e.getErrorCode().equals("ERROR_USER_NOT_FOUND"))
                                    notifyUser(getString(R.string.error_user_not_found));
                                    //Si la contraseña ingresada es incorrecta...
                                else if (e.getErrorCode().equals("ERROR_WRONG_PASSWORD"))
                                    notifyUser(getString(R.string.error_wrong_password));
                            } catch (FirebaseNetworkException e) {
                                //Si no existe conexión a internet...
                                notifyUser(getString(R.string.error_no_connection));
                            } catch (Exception e) {
                                notifyUser(task.getException().getMessage());
                            }
                        }

                    }
                });
    }

    //Función que obtiene el correo del usuario que se ha registrado en la aplicación y lo devuelve como String.
    public String getCurrentUserEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        return user.getEmail();
    }

    private void notifyUser(String message){
        //Creación del Alert Dialog que le indica al usuario que su cuenta ha sido creada exitosamente.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(R.string.error);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialogSuccesMSG = builder.create();
        dialogSuccesMSG.show();
    }




}