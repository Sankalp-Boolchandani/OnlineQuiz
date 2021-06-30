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

public class Addsubject extends AppCompatActivity {

    EditText e7,e8;
    Button b8;

    String url ="https://192.168.29.107:3456/phpFiles/addSub.php";
    String subid,subname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsubject);

        e7 = findViewById(R.id.edit7);
        e8 = findViewById(R.id.edit8);
        b8 = findViewById(R.id.button8);

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Addsub();


            }
        });
    }


    public void Addsub()
        {
            subid = e7.getText().toString();
            subname = e8.getText().toString();

            RequestQueue queue= Volley.newRequestQueue(Addsubject.this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        public void onResponse(String response)
                        {
                            Toast.makeText(getApplicationContext(),"Subject added successfully",Toast.LENGTH_LONG).show();
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
                    params.put("subid", subid);
                    params.put("subname", subname);
                    return params;
                }
            };
            queue.add(stringRequest);
        }

}
