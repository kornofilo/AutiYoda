package com.rialvik.autiyoda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PictogramsMenuActivity extends AppCompatActivity {

    Button manos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictograms_menu);

        manos = findViewById(R.id.mano);

        manos.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        //Intent paso= new Intent(v.getContext(),.class);
                        //startActivity(paso);
                    }
                }
        );
        /*
        dientes.setOnClickListener(
                new Button.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent paso= new Intent(v.getContext(),Lavar_manos.class);
                        startActivity(paso);
                    }
                }
        );*/
    }
}
