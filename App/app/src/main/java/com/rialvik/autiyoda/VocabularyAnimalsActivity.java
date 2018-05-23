package com.rialvik.autiyoda;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import java.util.Locale;


public class VocabularyAnimalsActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageView v_leon,v_vaca,v_jirafa,c_animales;
    private static final  int MY_DATA_CHECK_CODE = 1;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_animals);Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        c_animales = findViewById(R.id.ivna);
        v_leon = findViewById(R.id.ival);
        v_vaca = findViewById(R.id.ivav);
        v_jirafa = findViewById(R.id.ivaj);



        v_leon.setOnClickListener(this);
        v_vaca.setOnClickListener(this);
        v_jirafa.setOnClickListener(this);

    }
    public void b_color (View view) {
        Intent i = new Intent(this, VocabularyColorsActivity.class);
        startActivity(i);
    }
    public void b_categoria (View view) {
        Intent b = new Intent(this, VocabularyMenuActivity.class);
        startActivity(b);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ival:
                c_animales.setImageResource(R.drawable.n_leon);
                String speechleon = "León";
                mTts.speak(speechleon, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.ivaj:
                c_animales.setImageResource(R.drawable.n_jirafa);
                String speechjirafa = "Jirafa";
                mTts.speak(speechjirafa, TextToSpeech.QUEUE_FLUSH, null);
                break;

            case R.id.ivav:
                c_animales.setImageResource(R.drawable.n_vaca);
                String speechvaca = "Vaca";
                mTts.speak(speechvaca, TextToSpeech.QUEUE_FLUSH, null);
                break;




        }

    }
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            // success, create the TTS instance
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
                mTts = new TextToSpeech(this, this);
            else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }
    @Override
    public void onInit(int status) {
        mTts.setLanguage(new Locale(Locale.getDefault().getLanguage()));

    }
}
