package com.example.sharaddadhich.minorprojectcode_sphere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharaddadhich.minorprojectcode_sphere.Adapters.InternshipRecyclerViewAdapter;
import com.example.sharaddadhich.minorprojectcode_sphere.POJO.Internships;
import com.example.sharaddadhich.minorprojectcode_sphere.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InternshipsActivity extends AppCompatActivity {

    RecyclerView rvInternships;
    Button btnPostInternship,btnRefreshList;
    ArrayList<Internships> internshipses = new ArrayList<>();
    InternshipRecyclerViewAdapter internshipRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internships);

        rvInternships = (RecyclerView) findViewById(R.id.rv_internships);
        btnPostInternship = (Button) findViewById(R.id.btn_PostInternship);
        btnRefreshList = (Button) findViewById(R.id.btn_RefreshInternship);

        rvInternships.setLayoutManager(new LinearLayoutManager(this,1,true));

        internshipRecyclerViewAdapter = new InternshipRecyclerViewAdapter(internshipses,this);
        rvInternships.setAdapter(internshipRecyclerViewAdapter);

        getJsonData();

        btnRefreshList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internshipses = new ArrayList<Internships>();
                getJsonData();
            }
        });

        btnPostInternship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(InternshipsActivity.this,WriteInternshipActivity.class);
                startActivity(i);
                finish();
            }
        });




    }


    public void getJsonData(){

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://192.168.43.104:3000/internship/getInternship", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response == null) {
                                Toast.makeText(InternshipsActivity.this, "Null Response", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    Log.d("234234", "onResponse: " + response.toString());
                                    JSONArray jsonArray = response.getJSONArray("results");
                                    Log.d("234234", "onResponse: " + jsonArray.toString());
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        Log.d("234235", "onResponse: " + jsonArray.getJSONObject(i).toString());
                                        JSONObject j = jsonArray.getJSONObject(i);
                                        String inte = j.getString("internship");
                                        Internships intern = new Internships(inte);
                                        internshipses.add(intern);

                                    }

                                    internshipRecyclerViewAdapter.UpdateData(internshipses);

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
                Log.d("123123", "onErrorResponse: " + "Error in Fetching data=====");
                error.printStackTrace();
                Toast.makeText(InternshipsActivity.this, "Error in Fetching Data", Toast.LENGTH_SHORT).show();
            }
        }

        );

        RequestQueue request = Volley.newRequestQueue(InternshipsActivity.this);
        request.add(jsonObjectRequest);
    }
}
