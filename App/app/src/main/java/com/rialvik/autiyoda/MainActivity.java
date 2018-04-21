package com.rialvik.autiyoda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


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

        setContentView(R.layout.activity_main);

       /* //Fragment de Bienvenida
        // Verificamos si el usuario ingresa a la aplicación por primera vez.
        if (!sharedPreferences.getBoolean(
            MyOnboardingFragment.COMPLETED_ONBOARDING_PREF_NAME, false)) {
            //Ejecutamos el fragment de bienvenida.
            startActivity(new Intent(this, WelcomeFragment.class));
        }*/


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intentMain;
        int id = item.getItemId();

        if (id == R.id.nav_pictograms) {
        } else if (id == R.id.nav_vocabulary) {

        } else if (id == R.id.nav_maps) {
            intentMain = new Intent(MainActivity.this,MapsActivity.class);
            startActivity(intentMain);

        }  else if (id == R.id.nav_settings) {
            intentMain = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intentMain);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
