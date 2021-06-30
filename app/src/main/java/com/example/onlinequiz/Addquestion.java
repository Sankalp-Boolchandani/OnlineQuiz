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

public class Addquestion extends AppCompatActivity {

    EditText e9,e10,e11,e12,e13,e14,e15;
    Button b9;
    String url ="https://192.168.29.107:3456/phpFiles/addQue.php";
    String subjectid,question,op1,op2,op3,op4,ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);

        e9 = findViewById(R.id.edit9);
        e10 = findViewById(R.id.edit10);
        e11 = findViewById(R.id.edit11);
        e12 = findViewById(R.id.edit12);
        e13 = findViewById(R.id.edit13);
        e14 = findViewById(R.id.edit14);
        e15 = findViewById(R.id.edit15);
        b9 = findViewById(R.id.button9);

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addquestion();

            }
        });

    }

    public void addquestion()
    {

        subjectid = e9.getText().toString();
        question = e10.getText().toString();
        op1 = e11.getText().toString();
        op2 = e12.getText().toString();
        op3 = e13.getText().toString();
        op4 = e14.getText().toString();
        ans = e15.getText().toString();

        RequestQueue queue= Volley.newRequestQueue(Addquestion.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    public void onResponse(String response)
                    {
                        Toast.makeText(getApplicationContext(),"Question added successfully",Toast.LENGTH_LONG).show();
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
                params.put("subjectid", subjectid);
                params.put("question", question);
                params.put("op1", op1);
                params.put("op2", op2);
                params.put("op3", op3);
                params.put("op4", op4);
                params.put("ans", ans);
                return params;
            }
        };
        queue.add(stringRequest);
    }

}
