package com.rialvik.autiyoda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);


        //setOnClickListener para las opciones de Logout y Eliminar cuenta.
        Preference prefLogout, prefDeleteAccount, prefNightMode;

        prefNightMode = findPreference("nightMode_switch");

        prefNightMode.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intentLogout = new Intent(getActivity(), MainActivity.class);
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
        builder.setMessage(message)
                .setTitle(R.string.warning);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteAccount();
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


}