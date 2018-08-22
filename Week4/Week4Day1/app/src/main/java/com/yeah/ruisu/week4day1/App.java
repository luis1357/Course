package com.yeah.ruisu.week4day1;

import android.app.Application;
import android.support.annotation.NonNull;

import com.yeah.ruisu.week4day1.injection.AppComponent;
import com.yeah.ruisu.week4day1.injection.AppModule;
import com.yeah.ruisu.week4day1.injection.DaggerAppComponent;

public final class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}