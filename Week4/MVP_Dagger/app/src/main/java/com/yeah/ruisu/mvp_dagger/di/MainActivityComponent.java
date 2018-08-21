package com.yeah.ruisu.mvp_dagger.di;

import com.yeah.ruisu.mvp_dagger.di.scopes.Activity;
import com.yeah.ruisu.mvp_dagger.di.scopes.Application;
import com.yeah.ruisu.mvp_dagger.view.mainactivity.MainActivity;

import dagger.Component;
import dagger.Subcomponent;

@Application
@Subcomponent(modules = MyAppComponent.class)
public interface MainActivityComponent
{
    void inject(MainActivity mainActivity);
}
