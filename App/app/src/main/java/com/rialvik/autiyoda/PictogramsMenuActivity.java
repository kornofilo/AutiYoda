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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class PictogramsMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textViewUsername;
    String userEmail;

    private ListView listView;
    private CustomListViewAdapter customListViewAdapter;


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

        setContentView(R.layout.activity_pictograms_menu);
        drawerInitialization();

        listView = findViewById(R.id.habilities_list);
        ArrayList<ListViewElements> habilitiesList = new ArrayList<>();
        habilitiesList.add(new ListViewElements(R.drawable.hand_washing_3, getString(R.string.hand_washing),"Pasos: 6"));
        habilitiesList.add(new ListViewElements(R.drawable.make_the_bed_4, getString(R.string.make_the_bed),"Pasos: 6"));
        habilitiesList.add(new ListViewElements(R.drawable.tooth_brushing_3, getString(R.string.tooth_brushing),"Pasos: 7"));

        customListViewAdapter = new CustomListViewAdapter(this,habilitiesList);
        listView.setAdapter(customListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                feedScreenShow(position);
            }
        });

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

        }else if (id == R.id.nav_vocabulary) {
            startActivity(new Intent(this, VocabularyMenuActivity.class));
        }
        else if (id == R.id.nav_maps) {
            startActivity(new Intent(this, MapsActivity.class));
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

    void feedScreenShow(int position){
        Intent intent= new Intent(this,ScreenShowActivity.class);
        String pasos[];
        int imgPasos[];

        switch (position){
            case 0:
                pasos = this.getResources().getStringArray(R.array.hand_washing_steps);
                imgPasos = new int[] {R.drawable.hand_washing_1,R.drawable.hand_washing_2, R.drawable.hand_washing_3, R.drawable.hand_washing_4, R.drawable.hand_washing_5,R.drawable.hand_washing_6,R.drawable.congratulations};
                intent.putExtra("name", getString(R.string.hand_washing));
                intent.putExtra("num_elements",7);
                intent.putExtra("elements",pasos);
                intent.putExtra("img_elements",imgPasos);
                startActivity(intent);
                break;
            case 1:
                pasos = this.getResources().getStringArray(R.array.make_the_bed_steps);
                imgPasos = new int[] {R.drawable.make_the_bed_1,R.drawable.make_the_bed_2, R.drawable.make_the_bed_3, R.drawable.make_the_bed_4, R.drawable.make_the_bed_5,R.drawable.make_the_bed_6,R.drawable.congratulations};
                intent.putExtra("name", getString(R.string.make_the_bed));
                intent.putExtra("num_elements",7);
                intent.putExtra("elements",pasos);
                intent.putExtra("img_elements",imgPasos);
                startActivity(intent);
                break;
            case 2:
                pasos = this.getResources().getStringArray(R.array.tooth_brushing_steps);
                imgPasos = new int[] {R.drawable.tooth_brushing_1,R.drawable.tooth_brushing_2, R.drawable.tooth_brushing_3, R.drawable.tooth_brushing_4, R.drawable.tooth_brushing_5,R.drawable.tooth_brushing_6,R.drawable.tooth_brushing_7,R.drawable.congratulations};
                intent.putExtra("name", getString(R.string.tooth_brushing));
                intent.putExtra("num_elements",8);
                intent.putExtra("elements",pasos);
                intent.putExtra("img_elements",imgPasos);
                startActivity(intent);
                break;
        }
    }


}
