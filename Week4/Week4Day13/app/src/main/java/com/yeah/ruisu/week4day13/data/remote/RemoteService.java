package com.yeah.ruisu.week4day13.data.remote;

import com.yeah.ruisu.week4day13.models.ProfileData;
import com.yeah.ruisu.week4day13.models.UserData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RemoteService
{
    @GET("/search/users")
    Single<ProfileData> getProfileData(@Query("q") String userName);

    @GET()
    Single<UserData> getUserData(@Url String userName);
}
