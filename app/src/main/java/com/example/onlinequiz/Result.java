package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {

    TextView result;
    Button buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.resultText);
        buttonHome = findViewById(R.id.homeButton);

        Bundle obj = getIntent().getExtras();
         String questionMarks = obj.getString("m");

        Toast.makeText(getApplicationContext(),questionMarks,Toast.LENGTH_LONG).show();



    }
}