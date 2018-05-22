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
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textViewUsername;
    String userEmail;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Inicialización del Drawer principal.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        textViewUsername = navigationView.getHeaderView(0).findViewById(R.id.textViewUsername);

        //Obtenemos el correo del usuario registrado al llamar al la función "getCurrentUserEmail).
        userEmail = getCurrentUserEmail();
        textViewUsername.setText(userEmail);
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
    //Función que gestionas las acciones de cada uno de los elementos del drawer principal.
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_pictogram) {
            startActivity(new Intent(this, PictogramsMenuActivity.class));

        }else if (id == R.id.nav_vocabulary) {
            startActivity(new Intent(this, VocabularyMenuActivity.class));
        }
        else if (id == R.id.nav_maps) {
            startActivity(new Intent(this, MapsActivity.class));
        } else if (id == R.id.nav_help) {


        }
        else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Función que obtiene el correo del usuario que se ha registrado en la aplicación y lo devuelve como String.
    public String getCurrentUserEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        return user.getEmail();
    }
}
