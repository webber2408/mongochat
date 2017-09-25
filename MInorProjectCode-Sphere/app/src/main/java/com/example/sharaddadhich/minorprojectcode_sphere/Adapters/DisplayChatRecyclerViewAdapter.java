package com.example.sharaddadhich.minorprojectcode_sphere.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sharaddadhich.minorprojectcode_sphere.POJO.ChatData;
import com.example.sharaddadhich.minorprojectcode_sphere.R;

import java.util.ArrayList;

/**
 * Created by sharaddadhich on 25/09/17.
 */

public class DisplayChatRecyclerViewAdapter extends RecyclerView.Adapter<DisplayChatRecyclerViewAdapter.MessageViewHolder> {

    ArrayList<ChatData> chat;
    Context context;

    public DisplayChatRecyclerViewAdapter(ArrayList<ChatData> chat, Context context) {
        this.chat = chat;
        this.context = context;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisView = li.inflate(R.layout.chatdisplaylayout,parent,false);
        return new MessageViewHolder(thisView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {

        ChatData thisData = chat.get(position);
        holder.tvDisplaydata.setText(thisData.getName() + " : " +thisData.getMessage());

    }

    @Override
    public int getItemCount() {
        return chat.size();
    }

    public void UpdateChat(ArrayList<ChatData>chat)
    {
        this.chat = chat;
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{

        TextView tvDisplaydata;

        public MessageViewHolder(View itemView) {
            super(itemView);
            tvDisplaydata = (TextView) itemView.findViewById(R.id.tv_ChatMessage);
        }
    }

}
