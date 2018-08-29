package com.yeah.ruisu.volvocodingchallenge.data.RepositoryModule;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.yeah.ruisu.volvocodingchallenge.data.remote.RemoteServiceHelper;
import com.yeah.ruisu.volvocodingchallenge.data.remote.models.ForecastDatum;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository
{
    private MutableLiveData<List<ForecastDatum>> weatherForecastLiveData = new MutableLiveData<>();
    private RemoteServiceHelper MyRemoteServiceHelper;

    public RepositoryImpl(RemoteServiceHelper InRemoteServiceHelper)
    {
        this.MyRemoteServiceHelper = InRemoteServiceHelper;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getWeatherForecast(String WOEID, String year, String month, String day)
    {
        MyRemoteServiceHelper.getWeatherForecast(WOEID, year, month, day)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(weatherApiResponse ->
                                        weatherForecastLiveData.setValue(weatherApiResponse),
                                        Throwable::printStackTrace);
    }

    public MutableLiveData<List<ForecastDatum>> getWeatherForecastLiveData() {
        return weatherForecastLiveData;
    }
}
