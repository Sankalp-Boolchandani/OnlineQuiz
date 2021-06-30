package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Firstscreen extends AppCompatActivity {

    Button bst,btea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstscreen);

        bst = findViewById(R.id.buttonstu);
        btea = findViewById(R.id.buttonteacher);


        bst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Studentlogin.class);
                startActivity(i);
            }
        });


        btea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(a);
            }
        });

    }
}