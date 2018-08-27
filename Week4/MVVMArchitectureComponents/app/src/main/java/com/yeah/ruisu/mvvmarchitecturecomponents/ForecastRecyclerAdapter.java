package com.yeah.ruisu.mvvmarchitecturecomponents;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.models.Weather;
import com.yeah.ruisu.mvvmarchitecturecomponents.databinding.WeatherRecyclerItemBinding;

import java.util.List;

public class ForecastRecyclerAdapter
        extends RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder>
{
    private static final String TAG ="Adapter: ";

    private Context context;
    private List<List<Weather>> WeatherList;

    public ForecastRecyclerAdapter(Context context, List<List<Weather>> weatherList)
    {
        this.context = context;
        WeatherList = weatherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");

        WeatherRecyclerItemBinding MyBinder =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                                        R.layout.weather_recycler_item,
                                                        parent,
                                                        false);
        return new ViewHolder(MyBinder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Weather temp = WeatherList.get(position).get(0);

        String[] dateAndTime = temp.getDate().split(" ");

        holder.MyBinder
                .tvMain
                .setText(String.format(context.getResources()
                                                .getString(R.string.all_weatherMain),
                                                            temp.getMain(),
                                                            dateAndTime[0],
                                                            dateAndTime[1]));
        holder.MyBinder
                .tvDescription
                .setText(temp.getDescription());
        String imageUrl = "http://openweathermap.org/img/w/%s.png";
        String finalImageUrl = String.format(imageUrl, temp.getIcon());
        Glide.with(context).load(finalImageUrl).into(holder.MyBinder.ivWeatherIcon);
    }

    @Override
    public int getItemCount()
    {
        return WeatherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        WeatherRecyclerItemBinding MyBinder;

        public ViewHolder(WeatherRecyclerItemBinding InBinder)
        {
            super(InBinder.getRoot());
            this.MyBinder = InBinder;
        }


    }
}
