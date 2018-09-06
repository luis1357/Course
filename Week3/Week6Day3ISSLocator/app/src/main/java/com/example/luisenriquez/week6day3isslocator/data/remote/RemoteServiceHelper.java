package com.example.luisenriquez.week6day3isslocator.data.remote;

import com.example.luisenriquez.week6day3isslocator.data.remote.models.ISSResponse;
import com.example.luisenriquez.week6day3isslocator.utils.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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

    public Single<ISSResponse> getISSResponse (String lat, String lon)
    {
        Retrofit myRetro = new Retrofit.Builder()
                .client(MyOkHttpClnt)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.ISS_BASE_URL)
                .build();

        RemoteService myService = myRetro.create(RemoteService.class);

        return myService.getISSResponse(lat, lon);
    }
}
