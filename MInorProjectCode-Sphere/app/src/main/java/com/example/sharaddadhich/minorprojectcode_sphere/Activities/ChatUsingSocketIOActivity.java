package com.example.sharaddadhich.minorprojectcode_sphere.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sharaddadhich.minorprojectcode_sphere.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class ChatUsingSocketIOActivity extends AppCompatActivity {

    io.socket.client.Socket socket;
    EditText etMsg,etUsername;
    Button btnSend;
    TextView tvMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_using_socket_io);

        etMsg = (EditText) findViewById(R.id.et_MessagetoSend);
        tvMessages = (TextView) findViewById(R.id.tv_Messages);
        btnSend = (Button) findViewById(R.id.btn_SendMessage);
        etUsername = (EditText) findViewById(R.id.et_User);

        try {
            socket = IO.socket("http://chat.socket.io");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.connect();

        socket.on("sendMessage", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject recvMsg = (JSONObject) args[0];
                try {
                    String x= recvMsg.getString("name") + ":" + recvMsg.getString("message");
                    String z= tvMessages.getText().toString() + "\n" + x;
                    tvMessages.setText(z);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etMsg.getText().toString()))
                {
                    etMsg.setError("Please Write a Message");
                }
                else
                {
                    JSONObject sendData = new JSONObject();
                    try {
                        sendData.put("user",etUsername.getText().toString());
                        sendData.put("message",etMsg.getText().toString());

                        socket.emit("sendMessage",sendData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String z = tvMessages.getText().toString() + "\n" + etUsername.getText().toString() + ":" + etMsg.getText().toString();
                    etMsg.setText("");
                    tvMessages.setText(z);
                }
            }
        });

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {

            }
        });


    }
}
