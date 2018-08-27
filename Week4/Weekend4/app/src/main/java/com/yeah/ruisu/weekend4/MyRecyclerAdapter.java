package com.yeah.ruisu.weekend4;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyRecyclerAdapter
        extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>
{
    private static final String TAG ="Adapter: ";

    List<MyPlcsInfo> placesList;

    public MyRecyclerAdapter(List<MyPlcsInfo> inPlacesList)
    {
        this.placesList = inPlacesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");
        View MyView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_recycler_item, null);

        return new ViewHolder(MyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");

        MyPlcsInfo temp = placesList.get(position);

        if(temp != null)
        {
            holder.tvPlace.setText(temp.getPlcName());
            holder.tvAddress.setText(temp.getPlcAddress());

            Glide.with(holder.ivPic.getContext())
                    .load(temp.getPlcImgUrl())
                    .into(holder.ivPic);

        }
    }

    @Override
    public int getItemCount()
    {
        return placesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvPlace, tvAddress;
        private final ImageView ivPic;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.tvPlace = itemView.findViewById(R.id.tvPlaces);
            this.tvAddress = itemView.findViewById(R.id.tvPlcsAdrss);
            this.ivPic = itemView.findViewById(R.id.imageView);
        }


    }
}

