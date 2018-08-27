package com.example.mvpgithub.Di.Modules;


import com.example.mvpgithub.Misc.Constants;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class RetroFitModule {

    @Provides
    public HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    public OkHttpClient getOkhttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Authorization", String.format("%s", Constants.WEB.ACCESS_TOKEN));
            builder.addHeader("User-Agent", Constants.WEB.USER_AGENT);
            return chain.proceed(builder.build());
        }).addInterceptor(interceptor).build();
    }
}
