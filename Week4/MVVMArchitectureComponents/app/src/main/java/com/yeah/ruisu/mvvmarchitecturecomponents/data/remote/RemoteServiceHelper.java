package com.yeah.ruisu.mvvmarchitecturecomponents.data.remote;

import android.support.annotation.NonNull;

import com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.models.WeatherApiResponse;
import com.yeah.ruisu.mvvmarchitecturecomponents.utils.Constants;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper
{
    public Single<WeatherApiResponse> getWeatherForecast()
    {
        Retrofit MyRetroFit = getRetroFit(Constants.URLS.WEATHER_API_BASE_URL);
        RemoteService MyService = MyRetroFit.create(RemoteService.class);

        return MyService.getWeatherForecast("5210847",
                                            "b82b90e31b3b9b2723540c4b31afb584");
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
