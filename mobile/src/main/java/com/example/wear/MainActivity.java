package com.example.wear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void learnButton(View view) {
                Intent intent = new Intent(this, LearningActivity.class);

        startActivity(intent);

    }

    public void loginButton(View view) {
       // Intent intent = new Intent(this, LoginActivity.class);

        //startActivity(intent);

    }
}