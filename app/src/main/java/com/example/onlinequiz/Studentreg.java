package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Studentreg extends AppCompatActivity {

    EditText e16,e17,e18,e19,e20;
    Button b10;

    String url ="https://192.168.29.107:3456/phpFiles/sReg.php";
    String roll,name,pass,email,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentreg);

        e16 = findViewById(R.id.edit16);
        e17 = findViewById(R.id.edit17);
        e18 = findViewById(R.id.edit18);
        e19 = findViewById(R.id.edit19);
        e20 = findViewById(R.id.edit20);
        b10 = findViewById(R.id.button10);

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                studentreg();

            }
        });
    }

    public void studentreg()
    {

        roll = e16.getText().toString();
        name = e17.getText().toString();
        pass = e18.getText().toString();
        email = e19.getText().toString();
        contact = e20.getText().toString();
        RequestQueue queue= Volley.newRequestQueue(Studentreg.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    public void onResponse(String response)
                    {
                        Toast.makeText(getApplicationContext(),"Record added successfully",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener()
                {
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("roll", roll);
                params.put("name", name);
                params.put("pass", pass);
                params.put("email",email);
                params.put("contact",contact);
                return params;
            }
        };
        queue.add(stringRequest);
    }

}
