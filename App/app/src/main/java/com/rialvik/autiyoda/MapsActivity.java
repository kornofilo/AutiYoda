package com.rialvik.autiyoda;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_map_directory_menu, menu);

        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) item.getActionView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.map_directory_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        mMap.clear();
                        break;
                    case 1:
                        mMap.addMarker(new MarkerOptions().position(new LatLng(8.431509, -82.432466))
                                .snippet("Teléfono: 774-0128")
                                .title("Hospital Chiriquí"));
                        mMap.addMarker(new MarkerOptions().position(new LatLng(8.444829, -82.431818))
                                .snippet("Teléfono: 774-4048")
                                .title("Centro Médico Mae Lewis"));
                        mMap.addMarker(new MarkerOptions().position(new LatLng(8.433544, -82.455817))
                                .snippet("Teléfono: 775-4222")
                                .title("Hospital José Domingo de Obaldia"));
                        break;
                    case 2:
                        mMap.addMarker(new MarkerOptions().position(new LatLng(8.423333, -82.442381))
                                .snippet("- Horario: 9am-5pm")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                                .title("Dr. Rialvik"));
                        break;

            }


        }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
    });
        return true;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng david = new LatLng(8.427921, -82.425371);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(david,12));
    }
}
