package com.yeah.ruisu.volvocodingchallenge.data.RepositoryModule;

import android.arch.lifecycle.MutableLiveData;

import com.yeah.ruisu.volvocodingchallenge.data.remote.models.ForecastDatum;

import java.util.List;

public interface Repository
{
    MutableLiveData<ForecastDatum> weatherForecastLiveData = null;

    void getWeatherForecast(String WOEID, String year, String month, String day);
}
