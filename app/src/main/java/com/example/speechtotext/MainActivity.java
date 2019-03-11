package com.example.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView_result;
    ImageView imageView_mic;
    private final int REQ_VOICE_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView_result = findViewById(R.id.text_voice_result);

        imageView_mic = findViewById(R.id.btnmic);

        imageView_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVoice();
            }
        });
    }

    public void getVoice(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Please Speak Something");

        try {
            startActivityForResult(intent,REQ_VOICE_INPUT);
        }catch (ActivityNotFoundException a){


        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode){

            case REQ_VOICE_INPUT:{
                if(resultCode ==RESULT_OK && null !=data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView_result.setText(result.get(0));

                }
                break;
            }


        }
    }
}
