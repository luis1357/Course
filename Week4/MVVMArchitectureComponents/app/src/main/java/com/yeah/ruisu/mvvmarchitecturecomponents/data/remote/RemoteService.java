package com.yeah.ruisu.mvvmarchitecturecomponents.data.remote;

import com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.models.WeatherApiResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService
{
    @GET("data/2.5/forecast")
    Single<WeatherApiResponse> getWeatherForecast(@Query("id") String cityId,
                                                  @Query("APPID") String apiKey);
}
