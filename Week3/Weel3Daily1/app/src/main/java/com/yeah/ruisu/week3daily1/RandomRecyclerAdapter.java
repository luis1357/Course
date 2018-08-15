package com.yeah.ruisu.week3daily1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RandomRecyclerAdapter
        extends RecyclerView.Adapter<RandomRecyclerAdapter.ViewHolder>
{
    private static final String TAG ="Adapter";

    List<MyObject> RndObjtList = new ArrayList<>();

    public RandomRecyclerAdapter(List<MyObject> celebrityList)
    {
        this.RndObjtList = celebrityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");
        View MyView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.random_recycler_item, null);

        return new ViewHolder(MyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");

        MyObject RndObjtTemp = RndObjtList.get(position);

        if(RndObjtTemp != null)
        {
            /*
            int resourceId = holder.itemView.getResources()
                                                    .getIdentifier("@string/help",
                                                                    "string",
                                                                    holder.itemView
                                                                            .getContext()
                                                                            .getPackageName());

            holder.tvLuckyNumber.setText();
            holder.tvFood.setText(String.valueOf(celebrityTemp.age));
            holder.tvRandomFact.setText(String.valueOf(celebrityTemp.weight));*/
        }
    }

    @Override
    public int getItemCount()
    {
        return RndObjtList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvLuckyNumber;
        private final TextView tvFood;
        private final TextView tvRandomFact;
        private final ImageView ivPhoto;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.tvLuckyNumber = itemView.findViewById(R.id.tvLuckyNumber);
            this.tvFood = itemView.findViewById(R.id.tvFood);
            this.tvRandomFact = itemView.findViewById(R.id.tvRandomFact);
            this.ivPhoto = itemView.findViewById(R.id.ivRndmImg);
        }


    }
}
