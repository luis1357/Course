package com.yeah.ruisu.weekend5_walmartchallenge;

import android.app.Application;

import com.yeah.ruisu.weekend5_walmartchallenge.di.DaggerMainComponent;
import com.yeah.ruisu.weekend5_walmartchallenge.di.MainComponent;
import com.yeah.ruisu.weekend5_walmartchallenge.di.MainModule;

import dagger.internal.DaggerCollections;

public class AppController extends Application
{
    private MainComponent myMainComponet;

    public MainComponent getAppComponet()
    {
        if (myMainComponet == null)
        {
            myMainComponet = DaggerMainComponent.builder()
                                                .mainModule(new MainModule(this))
                                                .build();
        }

        return myMainComponet;
    }
}
