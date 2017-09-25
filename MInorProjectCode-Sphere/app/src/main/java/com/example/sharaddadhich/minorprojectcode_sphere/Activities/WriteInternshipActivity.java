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

public class WriteInternshipActivity extends AppCompatActivity {

    Button btnSubmitInternship;
    EditText etDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_internship);

        btnSubmitInternship = (Button) findViewById(R.id.btn_SubmitInternship);
        etDetails = (EditText) findViewById(R.id.et_writeInternship);


        btnSubmitInternship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkdata())
                {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("internship",etDetails.getText().toString());

                        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.43.104:3000/internship/addInternship",
                                jsonObject,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        Toast.makeText(WriteInternshipActivity.this, "Internship Posted", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(WriteInternshipActivity.this,InternshipsActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(WriteInternshipActivity.this, "Unable to Post Internship", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );

                        RequestQueue request = Volley.newRequestQueue(WriteInternshipActivity.this);
                        request.add(jsonObjectRequest);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(WriteInternshipActivity.this, "Unable to connect to server", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    etDetails.setError("Please Provide Some Data");
                }

            }
        });


    }

    public boolean checkdata()
    {
        if(TextUtils.isEmpty(etDetails.getText().toString()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
