package com.example.sharaddadhich.minorprojectcode_sphere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharaddadhich.minorprojectcode_sphere.R;

import org.json.JSONException;
import org.json.JSONObject;

public class WriteJobxActivity extends AppCompatActivity {

    EditText etCompany,etExperience;
    Button btnPostExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_jobx);

        etCompany = (EditText) findViewById(R.id.et_writeCompanyName);
        etExperience = (EditText) findViewById(R.id.et_writeExperience);
        btnPostExperience = (Button) findViewById(R.id.btn_SubmitExperience);


        btnPostExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkdata())
                {
                    JSONObject jsonObject = new JSONObject();

                    try {
                        jsonObject.put("companyName",etCompany.getText().toString());
                        jsonObject.put("experience",etExperience.getText().toString());

                        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.43.104:3000/jobex/addExperience",
                                jsonObject,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(WriteJobxActivity.this, "Experience Posted", Toast.LENGTH_SHORT).show();
                                        Intent gotoJob = new Intent(WriteJobxActivity.this,JobxActivity.class);
                                        startActivity(gotoJob);
                                        finish();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(WriteJobxActivity.this, "Error in Posting Experience", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );

                        RequestQueue requestQuee = Volley.newRequestQueue(WriteJobxActivity.this);
                        requestQuee.add(jsonObjectRequest);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                else
                {
                    Toast.makeText(WriteJobxActivity.this, "Please Provide Proper Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    public boolean checkdata()
    {
        if(TextUtils.isEmpty(etCompany.getText().toString()))
        {
            return false;
        }
        else if(TextUtils.isEmpty(etExperience.getText().toString()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
