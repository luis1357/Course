package com.example.luisenriquez.week6_day3_isss_j.data.remote;

import com.example.luisenriquez.week6_day3_isss_j.data.remote.models.ISSResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService
{
    @GET("iss-pass.json")
    Single<ISSResponse> getISSResponse(@Query("lat") String lat,
                                       @Query("lon") String lon);
}