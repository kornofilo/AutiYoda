package com.rialvik.autiyoda;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.Locale;

public class VocabularyFruitsActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageView v_limon,v_manzana,v_papaya,v_sandia,c_frutas;
    private static final  int MY_DATA_CHECK_CODE = 1;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_fruits);  Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        c_frutas =(ImageView) findViewById(R.id.ivnf);
        v_limon =(ImageView) findViewById(R.id.ivl);
        v_manzana =(ImageView) findViewById(R.id.ivm);
        v_papaya =(ImageView) findViewById(R.id.ivp);
        v_sandia =(ImageView) findViewById(R.id.ivs);


        v_limon.setOnClickListener(this);
        v_manzana.setOnClickListener(this);
        v_papaya.setOnClickListener(this);
        v_sandia.setOnClickListener(this);
    }
    public void b_animal (View view) {
        Intent i = new Intent(this, VocabularyAnimalsActivity.class);
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
            case R.id.ivl:
                c_frutas.setImageResource(R.drawable.n_limon);
                String speechlimon = "Limon";
                mTts.speak(speechlimon, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.ivm:
                c_frutas.setImageResource(R.drawable.n_manzana);
                String speechmanzana = "Manzana";
                mTts.speak(speechmanzana, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.ivp:
                c_frutas.setImageResource(R.drawable.n_papaya);
                String speechpapaya = "Papaya";
                mTts.speak(speechpapaya, TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.ivs:
                c_frutas.setImageResource(R.drawable.n_sandia);
                String speechsandia = "Sandía";
                mTts.speak(speechsandia, TextToSpeech.QUEUE_FLUSH, null);
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
