package com.yeah.ruisu.week3day4.data.remote;

import com.yeah.ruisu.week3day4.models.WeatherData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService
{
    @GET("data/2.5/forecast")
    Single<WeatherData> getWeatherData(@Query("zip") String zip,
                                       @Query("units") String units,
                                       @Query("appid") String appId);
}
