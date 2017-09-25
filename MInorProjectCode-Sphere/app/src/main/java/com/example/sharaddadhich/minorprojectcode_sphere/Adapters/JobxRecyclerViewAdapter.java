package com.example.sharaddadhich.minorprojectcode_sphere.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sharaddadhich.minorprojectcode_sphere.POJO.Jobx;
import com.example.sharaddadhich.minorprojectcode_sphere.R;

import java.util.ArrayList;

/**
 * Created by sharaddadhich on 25/09/17.
 */

public class JobxRecyclerViewAdapter extends RecyclerView.Adapter<JobxRecyclerViewAdapter.JobxViewHolder> {

    ArrayList<Jobx> jobx;
    Context context;


    public JobxRecyclerViewAdapter(ArrayList<Jobx> jobx, Context context) {
        this.jobx = jobx;
        this.context = context;
    }

    @Override
    public JobxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisView = li.inflate(R.layout.jobxlayout,parent,false);

        return new JobxViewHolder(thisView);
    }


    @Override
    public void onBindViewHolder(JobxViewHolder holder, int position) {

        Jobx thisJobx = jobx.get(position);
        holder.tvComapany.setText(thisJobx.getCompanyName());
        holder.tvExperience.setText(thisJobx.getExperience());

    }

    @Override
    public int getItemCount() {
        return jobx.size();
    }

    public void UpdateJobs(ArrayList<Jobx> jobs)
    {
        this.jobx = jobs;
        notifyDataSetChanged();
    }

    class JobxViewHolder extends RecyclerView.ViewHolder{

        TextView tvComapany,tvExperience;

        public JobxViewHolder(View itemView) {
            super(itemView);

            tvComapany = (TextView) itemView.findViewById(R.id.tv_jobCompany);
            tvExperience = (TextView) itemView.findViewById(R.id.tv_jobx);
        }
    }

}
