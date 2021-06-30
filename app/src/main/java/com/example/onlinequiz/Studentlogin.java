package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Studentlogin extends AppCompatActivity {
    Button blog,bsign;
    String roll, pass;
    String roll1,pass1;
    EditText e1,e2;
    String url ="https://192.168.29.107:3456/phpFiles/sLogin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        blog = findViewById(R.id.buttonlog);
        bsign = findViewById(R.id.buttonsign);
        e1= findViewById(R.id.editroll);
        e2= findViewById(R.id.editpass);

        bsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), Studentreg.class);
                startActivity(i);
            }
        });

        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();

            }
        });
    }

    void signin()
    {
        roll= e1.getText().toString();
        pass = e2.getText().toString();

        RequestQueue queue= Volley.newRequestQueue(Studentlogin.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++)
                    {

                        JSONObject person = array.getJSONObject(i);
                        roll1  = person.getString("rollno");
                        pass1   = person.getString("spass");
                        if(pass.equals(pass1))
                        {
                            Intent b= new Intent(getApplicationContext(),Studenthome.class);
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
