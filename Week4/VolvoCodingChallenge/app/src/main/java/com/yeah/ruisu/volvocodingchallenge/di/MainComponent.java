package com.yeah.ruisu.volvocodingchallenge.di;


import com.yeah.ruisu.volvocodingchallenge.ui.MainActivity;
import com.yeah.ruisu.volvocodingchallenge.ui.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent
{
    void inject(MainActivity mainActivity);
    void inject(MainViewModel mainViewModel);
}
