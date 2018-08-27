package com.yeah.ruisu.week4day2.di;

import com.yeah.ruisu.week4day2.ui.MainActivity;
import com.yeah.ruisu.week4day2.ui.MainViewModel;
import com.yeah.ruisu.week4day2.ui.Repo_RecyclerViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent
{
    void inject(MainActivity mainActivity);
    void inject(MainViewModel mainViewModel);

    void inject(Repo_RecyclerViewModel repo_recyclerViewModel);
}
