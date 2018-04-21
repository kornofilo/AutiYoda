package com.rialvik.autiyoda;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.google.firebase.auth.FirebaseAuth;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);

        //setOnClickListener para las opciones de Logout y Eliminar cuenta.
        Preference prefLogout, prefDeleteAccount;
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
                FirebaseAuth.getInstance().signOut();
                Intent intentLogout = new Intent(getActivity(), LoginActivity.class);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentLogout);
                return true;
            }
        });
    }
}