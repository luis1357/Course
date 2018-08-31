package com.yeah.ruisu.realvolvocodingchallenge.data.RepositoryModule

import android.arch.lifecycle.MutableLiveData
import com.yeah.ruisu.realvolvocodingchallenge.data.remote.models.ForecastDatum

interface Repository
{
    val weatherForecastLiveData : MutableLiveData<List<ForecastDatum>>

    fun getWeatherForecast(WOEID : String)
}