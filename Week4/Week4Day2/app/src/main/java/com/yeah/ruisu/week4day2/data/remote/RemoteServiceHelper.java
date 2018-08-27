package com.yeah.ruisu.week4day2.data.remote;

import com.yeah.ruisu.week4day2.data.remote.models.ReposDatum;
import com.yeah.ruisu.week4day2.data.remote.models.UserData;
import com.yeah.ruisu.week4day2.utils.Constants;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper
{
    private Retrofit getRetroFit(String baseUrl)
    {
        HttpLoggingInterceptor MyInterceptor = new HttpLoggingInterceptor();

        MyInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient MyClient = new OkHttpClient.Builder()
                .addInterceptor(MyInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(MyClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Single<UserData> getUserData(String userName)
    {
        Retrofit MyRetroFit = getRetroFit(Constants.URLS.GITHUB_BASE_URL);
        RemoteService MyService = MyRetroFit.create(RemoteService.class);

        return MyService.getUserData(userName);
    }

    public Single<List<ReposDatum>> getUserReposData(String userName)
    {
        Retrofit MyRetroFit = getRetroFit(Constants.URLS.GITHUB_BASE_URL);
        RemoteService MyService = MyRetroFit.create(RemoteService.class);

        return MyService.getUserReposData(userName);
    }

}
