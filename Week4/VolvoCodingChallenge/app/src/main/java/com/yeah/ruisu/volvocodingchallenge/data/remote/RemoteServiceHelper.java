package com.yeah.ruisu.volvocodingchallenge.data.remote;


import android.provider.SyncStateContract;

import com.yeah.ruisu.volvocodingchallenge.data.remote.models.CityID;
import com.yeah.ruisu.volvocodingchallenge.data.remote.models.ForecastDatum;
import com.yeah.ruisu.volvocodingchallenge.utils.Constants;

import java.util.List;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper
{
    public Single<CityID> getWOEID(String cityName)
    {
        Retrofit MyRetroFit = getRetroFit(Constants.URLS.WEATHER_API_BASE_URL);
        RemoteService MyService = MyRetroFit.create(RemoteService.class);

        return MyService.getWOEID(cityName);
    }

    public Single<List<ForecastDatum>> getWeatherForecast(String WOEID,
                                                          String year,
                                                          String month,
                                                          String day)
    {
        Retrofit MyRetroFit = getRetroFit(Constants.URLS.WEATHER_API_BASE_URL);
        RemoteService MyService = MyRetroFit.create(RemoteService.class);

        return MyService.getWeatherForecast(WOEID, year, month, day);
    }

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
}
