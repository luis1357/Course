package com.example.luisenriquez.week6day3isslocator.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luisenriquez.week6day3isslocator.R;

import java.util.List;

public class IssPassAdapter
        extends RecyclerView.Adapter<IssPassAdapter.ViewHolder>
{
    private static final String TAG ="Adapter: ";

    List<ISSPass> issPassList;

    public IssPassAdapter(List<ISSPass> inIssPassList)
    {
        this.issPassList = inIssPassList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");
        View MyView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iss_item, null);

        return new ViewHolder(MyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");

        ISSPass issPassTemp = issPassList.get(position);

        if(issPassTemp != null)
        {
            holder.tvTime.setText(issPassTemp.riseTime);
            holder.tvDuration.setText(issPassTemp.duration);

        }
    }

    @Override
    public int getItemCount()
    {
        return issPassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvTime, tvDuration;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.tvTime = itemView.findViewById(R.id.tvTime);
            this.tvDuration = itemView.findViewById(R.id.tvDuration);
        }


    }
}

