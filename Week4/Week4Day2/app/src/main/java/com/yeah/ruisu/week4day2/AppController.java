package com.yeah.ruisu.week4day2;

import android.app.Application;

import com.yeah.ruisu.week4day2.di.DaggerMainComponent;
import com.yeah.ruisu.week4day2.di.MainComponent;
import com.yeah.ruisu.week4day2.di.MainModule;

public class AppController extends Application
{
    private MainComponent myMainComponent;

    public MainComponent getAppComponent()
    {
        if (myMainComponent == null)
        {
            myMainComponent = DaggerMainComponent.builder()
                                                .mainModule(new MainModule(this))
                                                .build();
        }
        return myMainComponent;
    }
}
