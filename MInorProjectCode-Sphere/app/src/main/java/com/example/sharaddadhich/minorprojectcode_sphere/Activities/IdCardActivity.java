package com.example.sharaddadhich.minorprojectcode_sphere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sharaddadhich.minorprojectcode_sphere.R;

public class IdCardActivity extends AppCompatActivity {

    Button btnGotoChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card);

        btnGotoChat = (Button) findViewById(R.id.btn_gotoChat);
        btnGotoChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IdCardActivity.this,ChatUsingSocketIOActivity.class);
                finish();
                startActivity(i);
            }
        });

    }
}
