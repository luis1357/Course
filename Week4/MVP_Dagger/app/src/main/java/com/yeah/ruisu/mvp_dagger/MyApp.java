package com.yeah.ruisu.mvp_dagger;

import android.app.Application;

import com.yeah.ruisu.mvp_dagger.di.MyAppComponent;
import com.yeah.ruisu.mvp_dagger.di.MyAppModule;

public class MyApp extends Application
{
    private MyAppComponent appComponent;

    public MyAppComponent getAppComponent()
    {
        if(appComponent == null)
        {
            appComponent = createComponent();
        }
        return appComponent;
    }

    public MyAppComponent createComponent()
    {
        appComponent = DaggerMyAppComponent.builder()
                                            .myAppModule(new MyAppModule(this))
                                            .build();
    }
}
