package com.rialvik.autiyoda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VocabularyMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    TextView textViewUsername;
    String userEmail;

    ImageView c_frutas,c_animales,c_colores;
    private static final  int MY_DATA_CHECK_CODE = 1;

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

        setContentView(R.layout.activity_vocabulary_menu);
        drawerInitialization();

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        c_frutas =  findViewById(R.id.icf);
        c_animales= findViewById(R.id.ica);
        c_colores = findViewById(R.id.icc);
        c_frutas.setOnClickListener(this);
        c_animales.setOnClickListener(this);
        c_colores.setOnClickListener(this);

    }


    public void drawerInitialization(){
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

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.icf:
                Intent a = new Intent(VocabularyMenuActivity.this,VocabularyFruitsActivity.class);
                startActivity(a);
                break;
            case R.id.ica:

                Intent b = new Intent(VocabularyMenuActivity.this,VocabularyAnimalsActivity.class);
                startActivity(b);
                break;
            case R.id.icc:

                Intent c = new Intent(VocabularyMenuActivity.this,VocabularyColorsActivity.class);
                startActivity(c);
                break;

        }

    }
}
