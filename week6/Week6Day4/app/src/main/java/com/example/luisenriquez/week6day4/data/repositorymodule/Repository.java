package com.example.luisenriquez.week6day4.data.repositorymodule;

import android.arch.lifecycle.MutableLiveData;

import com.example.luisenriquez.week6day4.data.remote.models.MovieResponse;

public interface Repository
{
    MutableLiveData<MovieResponse> movieResponseLiveData = null;

    void getMovieResponse(String srchItm);
}
