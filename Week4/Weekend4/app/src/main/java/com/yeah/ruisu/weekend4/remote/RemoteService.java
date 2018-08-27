package com.yeah.ruisu.weekend4.remote;

import com.yeah.ruisu.weekend4.models.PlacesData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService {

    @GET("maps/api/place/nearbysearch/json")
    Single<PlacesData> getPlacesData(@Query("location") String coord,
                                     @Query("rankby") String distance,
                                     @Query("name") String name,
                                     @Query("key") String apiKey);
}
