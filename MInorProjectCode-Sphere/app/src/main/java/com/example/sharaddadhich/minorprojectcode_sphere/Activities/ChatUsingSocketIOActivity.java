package com.example.sharaddadhich.minorprojectcode_sphere.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharaddadhich.minorprojectcode_sphere.Adapters.DisplayChatRecyclerViewAdapter;
import com.example.sharaddadhich.minorprojectcode_sphere.POJO.ChatData;
import com.example.sharaddadhich.minorprojectcode_sphere.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class ChatUsingSocketIOActivity extends AppCompatActivity {

    Socket socket;
    EditText etMsg,etUsername;
    Button btnSend;
    String z;
    int flag=1;
    DisplayChatRecyclerViewAdapter displayChatRecyclerViewAdapter;

    ArrayList<ChatData> chat = new ArrayList<>();
    RecyclerView rvMessages;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_internships:
                finish();
                Intent x = new Intent(ChatUsingSocketIOActivity.this,InternshipsActivity.class);
                startActivity(x);
                return true;
            case R.id.menu_jobx:
                Intent gotojobx = new Intent(ChatUsingSocketIOActivity.this,JobxActivity.class);
                startActivity(gotojobx);
                return true;
            case R.id.menu_AboutUs:
                //Do Nothing till now
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_using_socket_io);

        rvMessages = (RecyclerView) findViewById(R.id.rv_ChatDisplay);
        etMsg = (EditText) findViewById(R.id.et_MessagetoSend);
        btnSend = (Button) findViewById(R.id.btn_SendMessage);
        etUsername = (EditText) findViewById(R.id.et_User);
        displayChatRecyclerViewAdapter = new DisplayChatRecyclerViewAdapter(chat,this);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        rvMessages.setAdapter(displayChatRecyclerViewAdapter);

        try {
            socket = IO.socket("http://192.168.43.104:4000");
            Toast.makeText(this, "Socket Assigned", Toast.LENGTH_SHORT).show();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.connect();

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject jsonObject  = new JSONObject();
                try {
                    jsonObject.put("name","Sharad");
                    jsonObject.put("message","Hi there Socket");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                socket.emit("input",jsonObject);

            }
        }).on("updatedMessages", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("123123", "call: " + args[0].toString() );
                JSONArray recvmsgarray = (JSONArray) args[0];

                for(int i=0; i<recvmsgarray.length();i++)
                {
                    try {
                        JSONObject recvMsg =  recvmsgarray.getJSONObject(i);
                        Log.d("123123", "call: " + recvMsg.getString("name"));
                        ChatData thisdata = new ChatData(recvMsg.getString("name"),recvMsg.getString("message"));
                        chat.add(thisdata);
                        displayChatRecyclerViewAdapter.UpdateChat(chat);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("987987", "call: " + z);
                flag=1;
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
                        sendData.put("name",etUsername.getText().toString());
                        sendData.put("message",etMsg.getText().toString());

                        socket.emit("input",sendData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }
}
