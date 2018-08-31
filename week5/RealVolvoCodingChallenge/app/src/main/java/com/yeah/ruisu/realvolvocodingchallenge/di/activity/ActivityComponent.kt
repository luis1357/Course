package com.yeah.ruisu.realvolvocodingchallenge.di.activity

import com.yeah.ruisu.realvolvocodingchallenge.ui.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent
{
    fun inject(mainActivity: MainActivity)
}