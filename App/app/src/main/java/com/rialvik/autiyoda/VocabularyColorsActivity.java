package com.rialvik.autiyoda;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import java.util.Locale;

public class VocabularyColorsActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener{

    ImageView v_verde,v_rojo,v_naranja,c_colores;
    private static final  int MY_DATA_CHECK_CODE = 1;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_colors); Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        c_colores =(ImageView) findViewById(R.id.ivnc);
        v_verde =(ImageView) findViewById(R.id.ivcv);
        v_rojo =(ImageView) findViewById(R.id.ivcr);
        v_naranja=(ImageView) findViewById(R.id.ivcn);


        v_verde.setOnClickListener(this);
        v_rojo.setOnClickListener(this);
        v_naranja.setOnClickListener(this);

    }
    public void b_fruta (View view) {
        Intent i = new Intent(this, VocabularyFruitsActivity.class);
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
            case R.id.ivcv:
                c_colores.setImageResource(R.drawable.n_verde);
                String speechverde = "Verde";
                mTts.speak(speechverde, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.ivcr:
                c_colores.setImageResource(R.drawable.n_rojo);
                String speechrojo = "Rojo";
                mTts.speak(speechrojo, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.ivcn:
                c_colores.setImageResource(R.drawable.n_naranja);
                String speechnaranja = "Naranja";
                mTts.speak(speechnaranja, TextToSpeech.QUEUE_FLUSH, null);
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
