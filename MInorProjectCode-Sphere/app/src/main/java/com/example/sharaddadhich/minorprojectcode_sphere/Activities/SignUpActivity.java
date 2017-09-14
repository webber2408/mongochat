package com.example.sharaddadhich.minorprojectcode_sphere.Activities;

import android.app.ProgressDialog;
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

public class SignUpActivity extends AppCompatActivity {

    EditText etName,etUsername,etEmail,etPhoneNo,etPassword,etConfirmPassword;
    Button btnSubmit;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = (EditText) findViewById(R.id.et_Name);
        etUsername = (EditText) findViewById(R.id.et_SignUpUserName);
        etEmail = (EditText) findViewById(R.id.et_SignUpEmail);
        etPhoneNo = (EditText) findViewById(R.id.et_Phone_No);
        etPassword = (EditText) findViewById(R.id.et_SignUpPassword);
        etConfirmPassword = (EditText) findViewById(R.id.et_SignUpConfirmPassword);
        btnSubmit = (Button) findViewById(R.id.btn_Register);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering User...");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateFields())
                {
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);
                    giveJsonData();
                }
            }
        });


    }

    public Boolean validateFields()
    {
        if(TextUtils.isEmpty(etName.getText().toString()))
        {
            etName.requestFocus();
            etName.setError("Please Enter Your Name");
            return false;
        }
        if(TextUtils.isEmpty(etUsername.getText().toString()))
        {
            etUsername.requestFocus();
            etUsername.setError("Please Enter Your Username");
            return false;
        }
        if(TextUtils.isEmpty(etEmail.getText().toString()))
        {
            etEmail.requestFocus();
            etEmail.setError("Please Enter Your Email");
            return false;
        }
        if(TextUtils.isEmpty(etPhoneNo.getText().toString()))
        {
            etPhoneNo.requestFocus();
            etPhoneNo.setError("Please Enter Your Enrollment No.");
            return false;
        }
        if(TextUtils.isEmpty(etPassword.getText().toString()))
        {
            etPassword.requestFocus();
            etPassword.setError("Password Cannot be left blank");
            return false;
        }
        if(TextUtils.isEmpty(etConfirmPassword.getText().toString()))
        {
            etConfirmPassword.requestFocus();
            etConfirmPassword.setError("Please Confirm Your Password");
            return false;
        }
        if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
        {
            etConfirmPassword.requestFocus();
            etConfirmPassword.setError("Passwords Does Not Match");
            return false;
        }
        return true;

    }


    public void giveJsonData()
    {
        JSONObject json = new JSONObject();
        try {
            json.put("name",etName.getText().toString());
            json.put("username",etUsername.getText().toString());
            json.put("contact",etPhoneNo.getText().toString());
            json.put("password",etPassword.getText().toString());
            json.put("email",etEmail.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://192.168.43.104:3000/users/register",
                json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(SignUpActivity.this, "Sucess In Sign In", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    //then we need to fire an intent.
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SignUpActivity.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        RequestQueue requestqueue = Volley.newRequestQueue(SignUpActivity.this);
        requestqueue.add(jsonObjectRequest);

    }


}
