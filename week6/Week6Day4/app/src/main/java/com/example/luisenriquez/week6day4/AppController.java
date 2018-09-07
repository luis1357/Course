package com.example.luisenriquez.week6day4;

import android.app.Application;

import com.example.luisenriquez.week6day4.di.DaggerMainComponent;
import com.example.luisenriquez.week6day4.di.MainComponent;
import com.example.luisenriquez.week6day4.di.MainModule;

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
