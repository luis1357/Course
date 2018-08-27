package com.yeah.ruisu.week4day2.data.remote;

import com.yeah.ruisu.week4day2.data.remote.models.ReposDatum;
import com.yeah.ruisu.week4day2.data.remote.models.UserData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService
{
    @GET("/users/{username}")
    Single<UserData> getUserData(@Path("username") String useName);

    @GET("/users/{username}/repos")
    Single<List<ReposDatum>> getUserReposData(@Path("username") String useName);
}
