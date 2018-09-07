package com.example.luisenriquez.week6day4.di;


import com.example.luisenriquez.week6day4.ui.MainActivity;
import com.example.luisenriquez.week6day4.ui.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent
{
    void inject(MainActivity mainActivity);
    void inject(MainViewModel mainViewModel);
}
