package com.example.luisenriquez.week6day3_iss.data.remote.models

import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteService
{
    @GET ("api/location/{WOEID}/{date}")
    fun getWeatherForecast(@Path("WOEID") WOEID:String,
                          @Path("date") date:String)
            : Deferred<List<ISSResponse>>;
}