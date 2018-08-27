package com.yeah.ruisu.weekend4.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yeah.ruisu.weekend4.Constants;
import com.yeah.ruisu.weekend4.models.PlacesData;

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

    public Single<PlacesData> getPlacesData(String name, String Coord)
    {
        Retrofit MyRtrFt = new Retrofit.Builder()
                                        .client(MyOkHttpClnt)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                        .baseUrl(Constants.BASE_URL)
                                        .build();

        RemoteService MyService = MyRtrFt.create(RemoteService.class);

        return MyService.getPlacesData(Coord, "distance",
                                        name, Constants.API_KEY);
    }
}
