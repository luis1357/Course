package com.yeah.ruisu.mvvmarchitecturecomponents.data.RepositoryModule;

import android.arch.lifecycle.MutableLiveData;

import com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.models.ApiList;

public interface Repository
{
    MutableLiveData<ApiList> weatherForecastLiveData = null;

    void getWeatherForecast();
}
