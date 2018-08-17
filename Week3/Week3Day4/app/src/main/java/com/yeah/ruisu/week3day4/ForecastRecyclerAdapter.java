package com.yeah.ruisu.week3day4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ForecastRecyclerAdapter
        extends RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder>
{
    private static final String TAG ="Adapter: ";

    List<Forecast> ForecastList;

    public ForecastRecyclerAdapter(List<Forecast> InForecastList)
    {
        this.ForecastList = InForecastList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");
        View MyView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_item, null);

        return new ViewHolder(MyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");

        Forecast ForeTemp = ForecastList.get(position);

        if(ForeTemp != null)
        {
            holder.tvForeHigh.setText(ForeTemp.maxTemp);
            holder.tvForeLow.setText(String.valueOf(ForeTemp.minTemp));
            holder.ivForeIcon.setImageResource(ForeTemp.ImageId);
        }
    }

    @Override
    public int getItemCount()
    {
        return ForecastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvForeHigh;
        private final TextView tvForeLow;
        private final ImageView ivForeIcon;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.tvForeHigh = itemView.findViewById(R.id.tvForeHigh);
            this.tvForeLow = itemView.findViewById(R.id.tvForeLow);
            this.ivForeIcon = itemView.findViewById(R.id.ivForeIcon);
        }


    }
}
