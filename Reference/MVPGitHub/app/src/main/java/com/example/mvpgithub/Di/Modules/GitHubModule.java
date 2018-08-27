package com.example.mvpgithub.Di.Modules;


import com.example.mvpgithub.Misc.Constants;
import com.example.mvpgithub.Network.API.Github;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {RetroFitModule.class})
public class GitHubModule {

    @Provides
    public Github getGithubApi(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.WEB.GITHUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(Github.class);
    }
}
