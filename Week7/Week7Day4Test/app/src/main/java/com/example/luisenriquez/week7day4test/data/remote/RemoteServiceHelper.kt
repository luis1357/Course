package com.example.luisenriquez.week7day4test.data.remote

import com.example.luisenriquez.week7day4test.data.remote.model.NumbersResponse
import com.example.luisenriquez.week7day4test.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import kotlinx.coroutines.experimental.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteServiceHelper
{
    fun getRandomNumbers() : Deferred<NumbersResponse>
    {
        val retrofit = getRetrofit(Constants.BASE_URL)
        val service = retrofit.create(RemoteService::class.java)

        return service.getRandomNumbers()
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