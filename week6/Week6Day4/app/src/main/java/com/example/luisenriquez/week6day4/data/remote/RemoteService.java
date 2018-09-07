package com.example.luisenriquez.week6day4.data.remote;

import com.example.luisenriquez.week6day4.data.remote.models.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService
{
    @GET("3/search/movie")
    Single<MovieResponse> getMovieResponse(@Query("api_key") String api_key,
                                           @Query("query") String query);
}
