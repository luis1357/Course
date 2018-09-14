package com.example.luisenriquez.week7day4test.di.application

import com.example.luisenriquez.week7day4test.di.activity.ActivityComponent
import com.example.luisenriquez.week7day4test.di.activity.ActivityModule
import com.example.luisenriquez.week7day4test.ui.activities.MainViewModel

import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, LiveDataModule::class])
interface ApplicationComponent
{
    fun newActivityComponent(activityModule: ActivityModule) : ActivityComponent

    fun inject(mainViewModel: MainViewModel)

}