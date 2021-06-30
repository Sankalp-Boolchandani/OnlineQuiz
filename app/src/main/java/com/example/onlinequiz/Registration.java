package com.example.onlinequiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    Button b1;
    EditText e3,e4,e5,e6;

    String url ="https://192.168.29.107:3456/phpFiles/tReg.php";
    String name,mail,pass,mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        b1 = findViewById(R.id.button7);
        e3 = findViewById(R.id.edit3);
        e4 = findViewById(R.id.edit4);
        e5 = findViewById(R.id.edit5);
        e6 = findViewById(R.id.edit6);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reguser();
            }
        });


    }





    public void reguser()
    {
        name = e3.getText().toString();
        mail = e4.getText().toString();
        pass = e5.getText().toString();
        mob = e6.getText().toString();
        RequestQueue queue= Volley.newRequestQueue(Registration.this);

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
                params.put("username", name);
                params.put("email", mail);
                params.put("password", pass);
                params.put("mobile",mob);
                return params;
            }
        };
        queue.add(stringRequest);
    }

}
