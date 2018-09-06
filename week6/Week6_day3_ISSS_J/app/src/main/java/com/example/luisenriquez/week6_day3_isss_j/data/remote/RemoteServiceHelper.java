package com.example.luisenriquez.week6_day3_isss_j.data.remote;

import com.example.luisenriquez.week6_day3_isss_j.data.remote.models.ISSResponse;
import com.example.luisenriquez.week6_day3_isss_j.utils.Constants;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper
{
    public Single<ISSResponse> getISSResponse()
    {
        Retrofit MyRetroFit = getRetroFit(Constants.URLS.ISS_API_BASE_URL);
        RemoteService MyService = MyRetroFit.create(RemoteService.class);

        return MyService.getISSResponse("33.925990299999995",
                                        "-84.476051");
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
