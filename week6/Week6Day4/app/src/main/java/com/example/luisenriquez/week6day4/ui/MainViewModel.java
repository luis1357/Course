package com.example.luisenriquez.week6day4.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.arch.lifecycle.Transformations;
import android.util.Log;

import com.example.luisenriquez.week6day4.AppController;
import com.example.luisenriquez.week6day4.data.remote.models.MovieResponse;
import com.example.luisenriquez.week6day4.data.repositorymodule.Repository;
import com.example.luisenriquez.week6day4.data.repositorymodule.RepositoryImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends AndroidViewModel
{
    public static final String TAG = "MainViewModel: ";

    @Inject
    Repository myRepo;

    public MainViewModel(@NonNull Application application)
    {
        super(application);

        ((AppController) application).getAppComponent().inject(this);
    }

    public LiveData<MovieResponse> getMovieResponse(String srchItm)
    {
        myRepo.getMovieResponse(srchItm);

        return Transformations.map(((RepositoryImpl) myRepo)
                        .getMovieResponseLiveData(),
                input ->
                {
                    Log.d(TAG, "getMovieResponse: " +
                            input.getResults().get(0).getTitle());


                    return input;
                });
    }
}
