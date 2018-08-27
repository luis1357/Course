package com.yeah.ruisu.mvvmarchitecturecomponents.di;

import com.yeah.ruisu.mvvmarchitecturecomponents.ui.MainActivity;
import com.yeah.ruisu.mvvmarchitecturecomponents.ui.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent
{
    void inject(MainActivity mainActivity);
    void inject(MainViewModel mainViewModel);
}
