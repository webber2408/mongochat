package com.example.sharaddadhich.minorprojectcode_sphere.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sharaddadhich.minorprojectcode_sphere.POJO.Internships;
import com.example.sharaddadhich.minorprojectcode_sphere.R;

import java.util.ArrayList;

/**
 * Created by sharaddadhich on 24/09/17.
 */

public class InternshipRecyclerViewAdapter extends RecyclerView.Adapter<InternshipRecyclerViewAdapter.InternshipViewHolder> {

    ArrayList<Internships> internship;
    Context context;

    public InternshipRecyclerViewAdapter(ArrayList<Internships> internship, Context context) {
        this.internship = internship;
        this.context = context;
    }

    @Override
    public InternshipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisView  = li.inflate(R.layout.internshiplayout,parent,false);

        return new InternshipViewHolder(thisView);
    }

    @Override
    public void onBindViewHolder(InternshipViewHolder holder, int position) {

        Internships thisintern = internship.get(position);
        holder.tvInternship.setText(thisintern.getData());

    }

    @Override
    public int getItemCount() {
        return internship.size();
    }

    public void UpdateData(ArrayList<Internships> internship)
    {
        this.internship = internship;
        notifyDataSetChanged();
    }

    class InternshipViewHolder extends RecyclerView.ViewHolder{

        TextView tvInternship;

        public InternshipViewHolder(View itemView) {
            super(itemView);

            tvInternship = (TextView) itemView.findViewById(R.id.tv_internship);
        }
    }


}
