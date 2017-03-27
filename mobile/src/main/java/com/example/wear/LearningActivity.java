package com.example.wear;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;


public class LearningActivity extends AppCompatActivity{
    TextView textView3;
    TextView textView;
    ImageButton play;
    TextToSpeech tts;
    int result;
    boolean playing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();


        // Capture the layout's TextView and set the string as its text

        textView3 = (TextView) findViewById(R.id.textView3);
        textView = (TextView) findViewById(R.id.textView);
        play = (ImageButton) findViewById(R.id.imageButton);
        tts = new TextToSpeech(LearningActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = tts.setLanguage(Locale.US);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Feature not supported in Your Device",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void showTranslation(View view)
    {
        textView3.setVisibility(View.VISIBLE);
    }

    public void playTranslation(View view)
    {
        if (!playing)
        {
            play.setImageResource(android.R.drawable.ic_media_pause);

            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                Toast.makeText(getApplicationContext(),
                        "Feature not supported in Your Device",
                        Toast.LENGTH_SHORT).show();
            } else {
                String text = textView.getText().toString();
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    while (tts.isSpeaking())
                        SystemClock.sleep(100);
                        playing = false;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                play.setImageResource(android.R.drawable.ic_media_play);
                            }
                        });

                }
            });


        }
        if (playing)
        {
            if (tts != null)
            {
                tts.stop();
            }
        }
    }
    public void negative(View view)
    {
        // TODO add logic
    }
    public void positive(View view)
    {
        // TODO add logic
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts!=null) {
            tts.stop();
            tts.shutdown();
        }

    }
}


