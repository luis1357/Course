package com.yeah.ruisu.week4day2.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.yeah.ruisu.week4day2.AppController;
import com.yeah.ruisu.week4day2.data.RepositoryModule.Repository;
import com.yeah.ruisu.week4day2.data.RepositoryModule.RepositoryImpl;
import com.yeah.ruisu.week4day2.data.remote.models.ReposDatum;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Repo_RecyclerViewModel extends AndroidViewModel
{
    @Inject
    Repository MyRepo;

    private Application myApplication;
    private MutableLiveData<String> message = new MutableLiveData<>();

    public Repo_RecyclerViewModel(@NonNull Application application)
    {
        super(application);

        ((AppController) application).getAppComponent().inject(this);
    }

    public LiveData<List<ReposDatum>> getUserReposData(String userName)
    {
        MyRepo.getUserData(userName);

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
}
