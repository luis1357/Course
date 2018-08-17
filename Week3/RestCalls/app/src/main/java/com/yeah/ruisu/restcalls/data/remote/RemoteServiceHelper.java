package com.yeah.ruisu.restcalls.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yeah.ruisu.restcalls.Constants;
import com.yeah.ruisu.restcalls.models.WeatherData;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper
{
    private static final String ZIP = "94040";
    private static final String APP_ID = "b1b15e88fa797225412429c1c50c122a1";

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

    public Call<ResponseBody> getWeatherData()
    {
        Retrofit MyRtrFt = new Retrofit.Builder()
                                        .client(MyOkHttpClnt)
                                        .baseUrl(Constants.WEATHER_BASE_URL)
                                        .build();

        RemoteService MyService = MyRtrFt.create(RemoteService.class);

        return MyService.getWeatherData(ZIP, APP_ID);
    }

    public Single<WeatherData> getWeatherDataSingle()
    {
        Retrofit MyRtrFt = new Retrofit.Builder()
                .client(MyOkHttpClnt)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.WEATHER_BASE_URL)
                .build();

        RemoteService MyService = MyRtrFt.create(RemoteService.class);

        return MyService.getWeatherData1(ZIP, APP_ID);
    }
}
