package com.yeah.ruisu.mvp_dagger.di;

import com.yeah.ruisu.mvp_dagger.di.scopes.Activity;
import com.yeah.ruisu.mvp_dagger.view.mainactivity.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule
{
    @Activity
    @Provides
    MainActivityPresenter providesMainActivityPresenter()
    {
        return new MainActivityPresenter();
    }
}
