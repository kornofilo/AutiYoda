package com.rialvik.autiyoda;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class VocabularyMenuActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView c_frutas,c_animales,c_colores;
    private static final  int MY_DATA_CHECK_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_menu);
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
