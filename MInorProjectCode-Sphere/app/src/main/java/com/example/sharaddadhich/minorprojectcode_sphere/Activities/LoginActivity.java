package com.example.sharaddadhich.minorprojectcode_sphere.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.sax.StartElementListener;
import android.support.v4.media.RatingCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sharaddadhich.minorprojectcode_sphere.POJO.User;
import com.example.sharaddadhich.minorprojectcode_sphere.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText etUSername,etPassword;
    TextView tvSignUp,tvForgotPassword;
    Button btnSignIn;
    ProgressDialog progressDialog;
    public static final String TAG = "Login Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUSername = (EditText) findViewById(R.id.et_LoginUsername);
        etPassword = (EditText) findViewById(R.id.et_LoginPassword);
        tvSignUp = (TextView) findViewById(R.id.tv_NewAccount);

        btnSignIn = (Button) findViewById(R.id.btn_SignIn);
        progressDialog = new ProgressDialog(this);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                finish();
                startActivity(i);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields())
                {
                    progressDialog.setMessage("Logging In....");
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);
                    try {
                        getjsondata();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



    }



    public boolean validateFields()
    {
        String Username = etUSername.getText().toString();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(etUSername.getText().toString()))
        {
            etUSername.requestFocus();
            etUSername.setError("Please Provide a Username");
            return false;
        }

        if(TextUtils.isEmpty(etPassword.getText().toString()))
        {
            etPassword.requestFocus();
            etPassword.setError("Please Provide With a Password");
            return false;
        }
        return true;
    }


    public void getjsondata() throws JSONException {
        JSONObject jsons = new JSONObject();
        jsons.put("user",new JSONObject());
        JSONObject json = jsons.getJSONObject("user");

        try{
            json.put("username",etUSername.getText().toString());
            json.put("password",etPassword.getText().toString());
        }catch (Exception e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.43.104:3000/users/authenticate", json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response == null) {
                                Log.d(TAG, "onResponse: " + response.toString());

                                Toast.makeText(LoginActivity.this, "Invalid Usernmae or Password", Toast.LENGTH_SHORT).show();
                                etPassword.setText("");

                            } else {
                                progressDialog.dismiss();
//                                String Name = response.get("name").toString();
//                                String Username = response.get("username").toString();
//                                String Email = response.get("email").toString();
//                                String E_no = response.get("e.no").toString();
//                                String Password = response.get("password").toString();
//
//                                User thisUser = new User(Name,Username,Email,E_no,Password);
                                Toast.makeText(LoginActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();

                                //Add an Intent to the Chat Activity

                            }
                        } catch (Exception e) {
                            Log.d(TAG, "onResponse: the exception" + response.toString());
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "There is an Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Invalid Usernmae or Password", Toast.LENGTH_SHORT).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(jsonObjectRequest);
    }
}
