package com.yeah.ruisu.realvolvocodingchallenge.data.remote

import com.yeah.ruisu.realvolvocodingchallenge.data.remote.models.ForecastDatum
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService
{
    @GET ("api/location/{WOEID}/{date}")
    fun getWeatherForecast(@Path("WOEID") WOEID:String,
                          @Path("date") date:String)
            : Deferred<List<ForecastDatum>>;
}