package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Studenthome extends AppCompatActivity {

    EditText subjectid;

    Button buttonPlay,buttonlog;
    String url ="https://192.168.29.107:3456/phpFiles/fetchQue.php";

    String question,optionA,optionB,optionC,optionD,sid,answers;
    String data=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenthome);

        buttonPlay = findViewById(R.id.play);
        buttonlog = findViewById(R.id.logButton);
        subjectid = findViewById(R.id.subjId);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toastdata();
            }
        });
    }

    void toastdata()
    {
         sid = subjectid.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(Studenthome.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product = array.getJSONObject(i);
                                question = product.getString("question");
                                optionA = product.getString("op1");
                                optionB = product.getString("op2");
                                optionC = product.getString("op3");
                                optionD = product.getString("op4");
                                answers = product.getString("ans");



                                break;
                              //  data= data + question +"\n"+ optionA+"\n"+ optionB+"\n"+optionC+"\n"+optionD+"\n";


                               // t1.append("Question=" + question + "OP1 =" + optionA + "OP2 =" + optionB + "OP3 =" + optionC+ "OP4 =" + optionD + "\n");
                            }
                            //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                            Intent i= new Intent(getApplicationContext(), Quiz.class);
                            i.putExtra("question1",question);
                            i.putExtra("op1",optionA);
                            i.putExtra("op2",optionA);
                            i.putExtra("op3",optionB);
                            i.putExtra("op4",optionC);
                            i.putExtra("sid",sid);
                            i.putExtra("ans",answers);

                            startActivity(i);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                    }


                })
        {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("subid", sid);
                return params;
            }
        };

        queue.add(stringRequest);
    }
}