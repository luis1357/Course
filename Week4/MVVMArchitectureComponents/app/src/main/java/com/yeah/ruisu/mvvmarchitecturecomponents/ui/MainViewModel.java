package com.yeah.ruisu.mvvmarchitecturecomponents.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.view.animation.Transformation;

import com.yeah.ruisu.mvvmarchitecturecomponents.AppController;
import com.yeah.ruisu.mvvmarchitecturecomponents.data.RepositoryModule.Repository;
import com.yeah.ruisu.mvvmarchitecturecomponents.data.RepositoryModule.RepositoryImpl;
import com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.models.ApiList;
import com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.models.Weather;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends AndroidViewModel
{
    @Inject
    Repository MyRepo;

    private Application myApplication;
    private MutableLiveData<String> message = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application)
    {
        super(application);

        ((AppController) application).getAppComponent().inject(this);
    }



    public LiveData<List<List<Weather>>> getWeatherForecast()
    {
        MyRepo.getWeatherForecast();

        return Transformations.map(((RepositoryImpl) MyRepo)
                                    .getWeatherForecastLiveData(),
                input ->
                {
                    List<List<Weather>> weaatherList = new ArrayList<>();

                    for(ApiList list : input)
                    {
                        list.getWeather().get(0).setDate(list.getDtTxt());
                        weaatherList.add(list.getWeather());
                    }
                    message.setValue("We're done!");
                    return weaatherList;
                });
    }

    public MutableLiveData<String> getMessage() {
        return message;
    }

    public void setMessage(MutableLiveData<String> message) {
        this.message = message;
    }
}
