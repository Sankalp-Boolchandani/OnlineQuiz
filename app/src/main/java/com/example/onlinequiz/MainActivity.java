package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button b5,b6;
    String mobile, pass;
    String mobile1,pass1;
EditText e1,e2;
    String url ="https://192.168.29.107:3456/phpFiles/tLogin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);


     e1= findViewById(R.id.edit1);
     e2= findViewById(R.id.edit2);


     b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), Registration.class);
                startActivity(i);

             }
        });

     b5.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             signin();

         }
     });

    }


    void signin()
    {
        mobile= e1.getText().toString();
        pass = e2.getText().toString();

        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);

     StringRequest stringRequest = new StringRequest(Request.Method.GET,
             url,new Response.Listener<String>() {

                 @Override
                 public void onResponse(String response) {
                     try {
                         JSONArray array = new JSONArray(response);
                         String  x="hello";
                         for (int i = 0; i < array.length(); i++)
                         {

                             JSONObject person = array.getJSONObject(i);
                             mobile1  = person.getString("tmobile");
                             pass1   = person.getString("tpass");
                               if(mobile.equals(mobile1))
                                {
                                       Intent b= new Intent(getApplicationContext(),Thome.class);
                                        startActivity(b);;
                                }
                         }

                     } catch (JSONException e) {
                         e.printStackTrace();
                     }

                 }
             },
             new Response.ErrorListener() {
                 @Override
                 public void onErrorResponse(VolleyError error) {

                     Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
                 }

             });
        queue.add(stringRequest);
    }
}
