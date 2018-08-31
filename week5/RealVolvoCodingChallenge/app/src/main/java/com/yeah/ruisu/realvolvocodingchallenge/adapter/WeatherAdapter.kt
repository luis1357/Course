package com.yeah.ruisu.realvolvocodingchallenge.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.yeah.ruisu.realvolvocodingchallenge.R
import com.yeah.ruisu.realvolvocodingchallenge.data.remote.models.ForecastDatum
import com.yeah.ruisu.realvolvocodingchallenge.databinding.RecyclerViewItemBinding
import com.yeah.ruisu.realvolvocodingchallenge.utils.Constants

class WeatherAdapter(private val weatherList: List<ForecastDatum>,
                     private val context: Context)
    : RecyclerView.Adapter<WeatherAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder
    {
        val itemBinding = DataBindingUtil.inflate<RecyclerViewItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.recycler_view_item,
                parent,
                false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int
    {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int)
    {
        var temp = weatherList[position]

        holder.binding.textViewMain.text = temp.weatherStateName
        holder.binding.textviewDescription.text = temp.created
        Glide.with(context)
                .load(Constants.BASE_URL +
                        Constants.WEATHER_ICON_URL +
                        temp.weatherStateAbbr +
                        ".png")
                .into(holder.binding.imageView)

    }

    class ViewHolder(var binding: RecyclerViewItemBinding) : RecyclerView.ViewHolder(binding.root)
}