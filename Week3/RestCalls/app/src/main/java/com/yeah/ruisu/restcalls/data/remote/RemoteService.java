package com.yeah.ruisu.restcalls.data.remote;

import com.yeah.ruisu.restcalls.models.WeatherData;

import java.util.Observable;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService
{
    @GET("data/2.5/forecast/")
    Call<ResponseBody> getWeatherData(@Query("zip") String zip,
                                      @Query("appid") String appId);

    @GET("data/2.5/forecast/")
    Single<WeatherData> getWeatherData1(@Query("zip") String zip,
                                        @Query("appid") String appId);
}
