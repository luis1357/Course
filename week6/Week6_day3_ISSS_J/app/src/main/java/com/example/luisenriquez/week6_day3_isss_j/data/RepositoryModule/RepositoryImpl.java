package com.example.luisenriquez.week6_day3_isss_j.data.RepositoryModule;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.RemoteServiceHelper;
import com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.models.ApiList;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository
{
    MutableLiveData<List<ApiList>> weatherForecastLiveData = new MutableLiveData<>();
    RemoteServiceHelper MyRemoteServiceHelper;

    public RepositoryImpl(RemoteServiceHelper InRemoteServiceHelper)
    {
        this.MyRemoteServiceHelper = InRemoteServiceHelper;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getWeatherForecast()
    {
        MyRemoteServiceHelper.getWeatherForecast()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(weatherApiResponse ->
                                        weatherForecastLiveData.setValue(weatherApiResponse.getApiList()),
                                        Throwable::printStackTrace);
    }

    public MutableLiveData<List<ApiList>> getWeatherForecastLiveData() {
        return weatherForecastLiveData;
    }
}
