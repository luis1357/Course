package com.example.luisenriquez.week7day4test.di.activity

import com.example.luisenriquez.week7day4test.ui.activities.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent
{
    fun inject(mainActivity: MainActivity)
}