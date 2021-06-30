package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Welcomesrc extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomesrc);


        iv = findViewById(R.id.imageView);
        Thread obj = new Thread(){
            public void run() {
                try {
                    sleep(3000);

                    Intent i = new Intent(getBaseContext(),Firstscreen.class);
                    startActivity(i);

                    finish();
                } catch (Exception e) {

                }
            }
        };

        obj.start();
    }
}
