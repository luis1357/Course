package com.yeah.ruisu.weekend3.data.remote;

import com.yeah.ruisu.weekend3.models.BooksData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService {

    @GET("books/v1/volumes")
    Single<BooksData> getBooksData(@Query("q") String keyword);
}
