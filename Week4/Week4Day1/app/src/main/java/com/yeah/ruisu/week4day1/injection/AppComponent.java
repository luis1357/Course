package com.yeah.ruisu.week4day1.injection;

import android.content.Context;

import com.yeah.ruisu.week4day1.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    App getApp();
}