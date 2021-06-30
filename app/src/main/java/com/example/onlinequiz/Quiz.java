package com.example.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class Quiz extends AppCompatActivity {



    int allQestiondb;
    String url ="https://192.168.29.107:3456/phpFiles/fetchQue.php";
    TextView textQuestion;
    RadioButton oprA,oprB,oprC,oprD;
    RadioButton userselection;
    Button nextButton,previousButton;
    String question,optionA,optionB,optionC,optionD;
    String option;
    RadioGroup r1;
    int count = 0;
    int marks=0;
String opans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Bundle x = getIntent().getExtras();
        final String q1 = x.getString("question1");
        String opa = x.getString("op1");
        String opb = x.getString("op2");
        String opc = x.getString("op3");
        String opd = x.getString("op4");
        opans = x.getString("ans");

        final String sub_id = x.getString("sid");

        oprA = findViewById(R.id.optiona);
        oprB = findViewById(R.id.optionb);
        oprC = findViewById(R.id.optionc);
        oprD = findViewById(R.id.optiond);
        textQuestion = findViewById(R.id.questiontext);
        r1= findViewById(R.id.radioGroup1);



        textQuestion.setText(q1);
        oprA.setText(opa);
        oprB.setText(opb);
        oprC.setText(opc);
        oprD.setText(opd);
        // int id= r1.getCheckedRadioButtonId();
        // userselection= findViewById(id);
        // option= userselection.getText().toString();

        // calmarks();


        nextButton = findViewById(R.id.nextButton);
        previousButton = findViewById(R.id.previousButton);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    calmarks();

                count = count + 1;


                RequestQueue queue = Volley.newRequestQueue(Quiz.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray array = new JSONArray(response);

                                    allQestiondb = array.length();


                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject product = array.getJSONObject(i);
                                        if (i == count) {
                                            question = product.getString("question");
                                            optionA = product.getString("op1");
                                            optionB = product.getString("op2");
                                            optionC = product.getString("op3");
                                            optionD = product.getString("op4");
                                            opans=product.getString("ans");
                                            break;
                                        }

                                        if(allQestiondb == count)
                                        {
                                            Intent b = new Intent(getApplicationContext(),Result.class);
                                            b.putExtra("m",String.valueOf(marks));
                                            startActivity(b);
                                        }


                                        //  data= data + question +"\n"+ optionA+"\n"+ optionB+"\n"+optionC+"\n"+optionD+"\n";


                                        // t1.append("Question=" + question + "OP1 =" + optionA + "OP2 =" + optionB + "OP3 =" + optionC+ "OP4 =" + optionD + "\n");


                                    }

                                    textQuestion.setText(question);
                                    oprA.setText(optionA);
                                    oprB.setText(optionB);
                                    oprC.setText(optionC);
                                    oprD.setText(optionD);
                                                 calmarks();
                                    //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("subid", sub_id);
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });

    }

    void calmarks()
    {
        int id= r1.getCheckedRadioButtonId();
        userselection= findViewById(id);
        option= userselection.getText().toString();

        if (option.equals(opans)) {
            marks = marks + 5;
        }
    }
}

