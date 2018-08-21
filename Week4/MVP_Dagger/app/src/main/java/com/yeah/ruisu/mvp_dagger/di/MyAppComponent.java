package com.yeah.ruisu.mvp_dagger.di;

import com.yeah.ruisu.mvp_dagger.MyApp;
import com.yeah.ruisu.mvp_dagger.di.scopes.Application;

import dagger.Component;

@Application
@Component(modules = MyAppComponent.class)
public interface MyAppComponent
{
    MainActivityComponent newActivityComponent(MainActivityModule module);

    void inject(MyApp myApp);
}
