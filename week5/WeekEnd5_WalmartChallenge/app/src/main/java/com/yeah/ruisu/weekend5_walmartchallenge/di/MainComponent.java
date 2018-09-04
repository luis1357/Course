package com.yeah.ruisu.weekend5_walmartchallenge.di;

import com.yeah.ruisu.weekend5_walmartchallenge.ui.MainActivity;
import com.yeah.ruisu.weekend5_walmartchallenge.ui.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent
{
    void inject (MainActivity mainActivity);
    void inject (MainViewModel mainViewModel);
}
