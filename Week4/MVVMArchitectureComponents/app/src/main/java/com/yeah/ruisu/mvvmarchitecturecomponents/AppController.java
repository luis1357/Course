package com.yeah.ruisu.mvvmarchitecturecomponents;

import android.app.Application;

import com.yeah.ruisu.mvvmarchitecturecomponents.di.DaggerMainComponent;
import com.yeah.ruisu.mvvmarchitecturecomponents.di.MainComponent;
import com.yeah.ruisu.mvvmarchitecturecomponents.di.MainModule;

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
