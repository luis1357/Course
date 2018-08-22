package com.yeah.ruisu.week4day14.Repos.Remote;



import com.yeah.ruisu.week4day14.Repos.model.ReposDatum;
import com.yeah.ruisu.week4day14.Repos.model.UserData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RemoteService
{
    @GET("/users/{username}")
    Single<UserData> getUserData(@Path("username") String useName);

    @GET("/users/{username}/repos")
    Single<List<ReposDatum>> getUserReposData(@Path("username") String useName);
}
