package com.aadarshamdavad.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aadarshamdavad.app.R;
import com.aadarshamdavad.app.activity.ActivitiesDetailActivity;
import com.aadarshamdavad.app.db_model.ActivitydbInfo;
import com.aadarshamdavad.app.fragment.ActivitiesFragment;

import java.util.ArrayList;

public class ActivitiesListAdapter extends RecyclerView.Adapter<ActivitiesListAdapter.MyViewHolder> {

    private Context mContext;
    //    private ArrayList<ActivitiesInfo> activitiesInfoArrayList = new ArrayList<>();
    private LayoutInflater inflater;
    ActivitiesFragment activitiesFragment;
    private ArrayList<ActivitydbInfo> arr_list = new ArrayList<>();

//    public ActivitiesListAdapter(Context mContext,
//                                 ArrayList<ActivitiesInfo> activitiesInfoArrayList,
//                                 ActivitiesFragment activitiesFragment) {
//        this.mContext = mContext;
//        this.activitiesInfoArrayList = activitiesInfoArrayList;
//        inflater = LayoutInflater.from(mContext);
//        this.activitiesFragment = activitiesFragment;
//    }

    public ActivitiesListAdapter(Context mContext,
                                 ArrayList<ActivitydbInfo> activitiesInfoArrayList,
                                 ActivitiesFragment activitiesFragment) {
        this.mContext = mContext;
        this.arr_list = activitiesInfoArrayList;
        inflater = LayoutInflater.from(mContext);
        this.activitiesFragment = activitiesFragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.adapter_activities_list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final String activitiesDetail = arr_list.get(position).activity_desc;
        final String activitiesTitle = arr_list.get(position).activity_title;

        holder.tvTitle.setText(activitiesTitle);

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                activitiesFragment.ActivitiesDetailFragment(activtiesDesc);
                Intent intent = new Intent(mContext, ActivitiesDetailActivity.class);
                intent.putExtra("activitiesTitle", activitiesTitle);
                intent.putExtra("activitiesDetail", activitiesDetail);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }
    }

}
