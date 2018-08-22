package com.yeah.ruisu.week4day14.Repos.Remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yeah.ruisu.week4day14.Constants;
import com.yeah.ruisu.week4day14.Repos.model.ReposDatum;
import com.yeah.ruisu.week4day14.Repos.model.UserData;


import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper
{
    private OkHttpClient MyOkHttpClnt;
    private static RemoteServiceHelper INSTANCE;

    private RemoteServiceHelper()
    {
        HttpLoggingInterceptor MyInterceptor = new HttpLoggingInterceptor();

        MyInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        MyOkHttpClnt = new OkHttpClient.Builder()
                .addInterceptor(MyInterceptor)
                .build();
    }

    public static RemoteServiceHelper getINSTANCE()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new RemoteServiceHelper();
        }

        return INSTANCE;
    }

    public Single<UserData> getUserData(String userName)
    {
        Retrofit MyRtrFt = new Retrofit.Builder()
                .client(MyOkHttpClnt)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.GITHUB_BASE_URL)
                .build();

        RemoteService MyService = MyRtrFt.create(RemoteService.class);

        return MyService.getUserData(userName);
    }

    public Single<List<ReposDatum>> getUserReposData(String userName)
    {
        Retrofit MyRtrFt = new Retrofit.Builder()
                .client(MyOkHttpClnt)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.GITHUB_BASE_URL)
                .build();

        RemoteService MyService = MyRtrFt.create(RemoteService.class);

        return MyService.getUserReposData(userName);
    }

}
