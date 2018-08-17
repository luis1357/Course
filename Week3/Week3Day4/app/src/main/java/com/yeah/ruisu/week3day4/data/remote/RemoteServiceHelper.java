package com.yeah.ruisu.week3day4.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yeah.ruisu.week3day4.Constants;
import com.yeah.ruisu.week3day4.models.WeatherData;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper
{
    private static final String APP_ID = "4f6ec9e5b0634a703296394ec23bf736";

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

    public Single<WeatherData> getWeatherData(String ZIP,
                                                    String Units)
    {
        Retrofit MyRtrFt = new Retrofit.Builder()
                .client(MyOkHttpClnt)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.WEATHER_BASE_URL)
                .build();

        RemoteService MyService = MyRtrFt.create(RemoteService.class);

        return MyService.getWeatherData(ZIP, Units, APP_ID);
    }
}
