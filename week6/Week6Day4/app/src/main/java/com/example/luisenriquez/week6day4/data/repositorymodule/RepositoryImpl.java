package com.example.luisenriquez.week6day4.data.repositorymodule;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.example.luisenriquez.week6day4.data.remote.RemoteServiceHelper;
import com.example.luisenriquez.week6day4.data.remote.models.MovieResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository
{
    private MutableLiveData<MovieResponse> movieResponseLiveData = new MutableLiveData<>();
    private RemoteServiceHelper MyRemoteServiceHelper;

    public RepositoryImpl(RemoteServiceHelper InRemoteServiceHelper)
    {
        this.MyRemoteServiceHelper = InRemoteServiceHelper;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getMovieResponse(String srchItm)
    {
        MyRemoteServiceHelper.getMovieResponse(srchItm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieApiResponse ->
                                movieResponseLiveData.setValue(movieApiResponse),
                        Throwable::printStackTrace);
    }

    public MutableLiveData<MovieResponse> getMovieResponseLiveData()
    {
        return movieResponseLiveData;
    }

}
