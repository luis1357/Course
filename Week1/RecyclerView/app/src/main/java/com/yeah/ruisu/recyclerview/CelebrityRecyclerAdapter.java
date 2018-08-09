package com.yeah.ruisu.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CelebrityRecyclerAdapter
        extends RecyclerView.Adapter<CelebrityRecyclerAdapter.ViewHolder>
{
    private static final String TAG ="Adapter";

    List<Celebrity> celebrityList = new ArrayList<>();

    public CelebrityRecyclerAdapter(List<Celebrity> celebrityList)
    {
        this.celebrityList = celebrityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");
        View MyView = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.celebrity_recycler_item, null);

        return new ViewHolder(MyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");

        Celebrity celebrityTemp = celebrityList.get(position);

        if(celebrityTemp != null)
        {
            holder.tvCelebrityname.setText(celebrityTemp.name);
            holder.tvCelebrityAge.setText(String.valueOf(celebrityTemp.age));
            holder.tvCelebrityWeight.setText(String.valueOf(celebrityTemp.weight));
        }
    }

    @Override
    public int getItemCount()
    {
        return celebrityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvCelebrityname;
        private final TextView tvCelebrityAge;
        private final TextView tvCelebrityWeight;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.tvCelebrityname = itemView.findViewById(R.id.tvCelebNameRecycler);
            this.tvCelebrityAge = itemView.findViewById(R.id.tvCelebAgeRecycler);
            this.tvCelebrityWeight = itemView.findViewById(R.id.tvCelebWeightRecycler);
        }


    }
}
