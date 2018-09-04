package com.yeah.ruisu.weekend5_walmartchallenge.data.remote;

import com.yeah.ruisu.weekend5_walmartchallenge.data.remote.models.WalmartSearchResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService
{
    @GET("v1/search")
    Single<WalmartSearchResult> getSearchResults(@Query("query") String keyWord,
                                                 @Query("format") String rspnsFrmt,
                                                 @Query("apiKey") String apiKey);
}
