package com.example.luisenriquez.week6day4.data.remote;

import com.example.luisenriquez.week6day4.data.remote.models.MovieResponse;
import com.example.luisenriquez.week6day4.utils.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper
{
    public Single<MovieResponse> getMovieResponse(String srchItm)
    {
        Retrofit myRetro = getRetroFit(Constants.MOVIE_API_BASE_URL);
        RemoteService myService = myRetro.create(RemoteService.class);

        return myService.getMovieResponse(Constants.MOVIE_API_KEY,
                                            srchItm);
    }

    private Retrofit getRetroFit(String baseUrl)
    {
        HttpLoggingInterceptor MyInterceptor = new HttpLoggingInterceptor();

        MyInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient MyClient = new OkHttpClient.Builder()
                .addInterceptor(MyInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(MyClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
