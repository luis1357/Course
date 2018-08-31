package com.yeah.ruisu.realvolvocodingchallenge.di.application

import com.yeah.ruisu.realvolvocodingchallenge.di.activity.ActivityComponent
import com.yeah.ruisu.realvolvocodingchallenge.di.activity.ActivityModule
import com.yeah.ruisu.realvolvocodingchallenge.ui.MainViewModel
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, LiveDataModule::class])
interface ApplicationComponent
{
    fun newActivityComponent(activityModule: ActivityModule) : ActivityComponent

    fun inject(mainViewModel: MainViewModel)
}