package com.yeah.ruisu.week4day13.data.remote;

import android.provider.SyncStateContract;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yeah.ruisu.week4day13.Constants;
import com.yeah.ruisu.week4day13.models.ProfileData;
import com.yeah.ruisu.week4day13.models.UserData;

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

    public Single<ProfileData> getProfileData(String userName)
    {
        Retrofit MyRtrFt = new Retrofit.Builder()
                .client(MyOkHttpClnt)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.GITHUB_BASE_URL)
                .build();

        RemoteService MyService = MyRtrFt.create(RemoteService.class);

        return MyService.getProfileData(userName);
    }

    public Single<UserData> getUserData(String userName)
    {
        Retrofit MyRtrFt = new Retrofit.Builder()
                .client(MyOkHttpClnt)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RemoteService MyService = MyRtrFt.create(RemoteService.class);

        return MyService.getUserData(userName);
    }
}
