package com.example.luisenriquez.weekend6.data.remote;

import com.example.luisenriquez.weekend6.data.remote.models.Example;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RemoteService
{
    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */
    @GET("api/place/nearbysearch/json?sensor=true")
    Call<Example> getNearbyPlaces(@Query("type") String type,
                                  @Query("location") String location,
                                  @Query("radius") int radius,
                                  @Query("key") String APIKEY);
}
