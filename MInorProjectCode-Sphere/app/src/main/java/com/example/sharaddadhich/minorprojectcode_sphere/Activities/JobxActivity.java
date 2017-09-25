package com.example.sharaddadhich.minorprojectcode_sphere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharaddadhich.minorprojectcode_sphere.Adapters.JobxRecyclerViewAdapter;
import com.example.sharaddadhich.minorprojectcode_sphere.POJO.Internships;
import com.example.sharaddadhich.minorprojectcode_sphere.POJO.Jobx;
import com.example.sharaddadhich.minorprojectcode_sphere.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JobxActivity extends AppCompatActivity {

    RecyclerView rvJobx;
    Button btnPostJobx,btnRefreshJobx;
    JobxRecyclerViewAdapter jobxRecyclerViewAdapter;
    ArrayList<Jobx> jobx = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobx);

        btnPostJobx = (Button) findViewById(R.id.btn_PostExperience);
        btnRefreshJobx = (Button) findViewById(R.id.btn_RefreshJobx);
        rvJobx = (RecyclerView) findViewById(R.id.rv_Jobx);

        rvJobx.setLayoutManager(new LinearLayoutManager(this,1,true));
        jobxRecyclerViewAdapter = new JobxRecyclerViewAdapter(jobx,this);
        rvJobx.setAdapter(jobxRecyclerViewAdapter);

        getJosnData();

        btnPostJobx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gotowrite = new Intent(JobxActivity.this,WriteJobxActivity.class);
                startActivity(gotowrite);
                finish();

            }
        });


        btnRefreshJobx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJosnData();
            }
        });


    }

    public void getJosnData()
    {
        final JSONObject jsonObject = new JSONObject();
        final JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(Request.Method.GET, "http://192.168.43.104:3000/jobex/getExperience", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response == null) {
                                Toast.makeText(JobxActivity.this, "Null Response from Server", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("1234512345", "onResponse: " + response.toString());
                                try {
                                    JSONArray jsonArray = response.getJSONArray("results");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject j = jsonArray.getJSONObject(i);
                                        String comp = j.getString("companyName");
                                        String exp = j.getString("experience");
                                        Jobx job = new Jobx(comp, exp);
                                        jobx.add(job);

                                    }

                                    jobxRecyclerViewAdapter.UpdateJobs(jobx);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JobxActivity.this, "Error In Fetching Data", Toast.LENGTH_SHORT).show();
            }
        }
        );

        RequestQueue requestquee = Volley.newRequestQueue(JobxActivity.this);
        requestquee.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(JobxActivity.this,ChatUsingSocketIOActivity.class);
        startActivity(i);
        finish();
    }
}
