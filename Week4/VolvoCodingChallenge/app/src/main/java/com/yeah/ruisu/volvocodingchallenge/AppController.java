package com.yeah.ruisu.volvocodingchallenge;

import android.app.Application;

import com.yeah.ruisu.volvocodingchallenge.di.DaggerMainComponent;
import com.yeah.ruisu.volvocodingchallenge.di.MainComponent;
import com.yeah.ruisu.volvocodingchallenge.di.MainModule;


public class AppController extends Application
{
    private MainComponent MyMainComponent;

    public MainComponent getAppComponent()
    {
        if (MyMainComponent == null)
        {
            MyMainComponent = DaggerMainComponent.builder()
                                                .mainModule(new MainModule(this))
                                                .build();
        }
        return MyMainComponent;
    }
}
