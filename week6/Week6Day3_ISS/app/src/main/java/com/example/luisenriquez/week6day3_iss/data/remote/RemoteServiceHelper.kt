package com.example.luisenriquez.week6day3_iss.data.remote.models

import android.provider.SyncStateContract
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.yeah.ruisu.realvolvocodingchallenge.data.remote.models.ForecastDatum
import com.yeah.ruisu.realvolvocodingchallenge.utils.Constants
import kotlinx.coroutines.experimental.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.LocalDate
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteServiceHelper
{
    fun getWeatherForecast(WOEID : String) : Deferred<List<ForecastDatum>>
    {
        val retrofit = getRetrofit(Constants.BASE_URL)
        val service = retrofit.create(RemoteService::class.java)
        val localDate = LocalDate().plusDays(1)

        return service.getWeatherForecast(WOEID, localDate.toString("YYYY/MM/dd"))
    }

    private fun getRetrofit (baseUrl : String) : Retrofit
    {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                                        .addInterceptor(interceptor)
                                        .build()

        return Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addCallAdapterFactory(CoroutineCallAdapterFactory())
                        .build()
    }


}