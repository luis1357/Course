package com.yeah.ruisu.volvocodingchallenge.data.remote;

import com.yeah.ruisu.volvocodingchallenge.data.remote.models.CityID;
import com.yeah.ruisu.volvocodingchallenge.data.remote.models.ForecastDatum;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService
{
    @GET("api/location/search/")
    Single<CityID> getWOEID(@Query("query") String cityName);

    @GET("api/location/{WOEID}/{year}/{month}/{day}/")
    Single<List<ForecastDatum>> getWeatherForecast(@Path("WOEID") String WOEID,
                                                   @Path("year") String year,
                                                   @Path("month") String month,
                                                   @Path("day") String day);
}
