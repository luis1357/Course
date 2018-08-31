package com.yeah.ruisu.realvolvocodingchallenge.di.activity

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import com.yeah.ruisu.realvolvocodingchallenge.ui.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule (private val activity: AppCompatActivity)
{
    @Provides
    @ActivityScope
    fun getMainViewModel(): MainViewModel
    {
        /* Instantiating the MainViewModel. */
        return ViewModelProviders.of(activity).get(MainViewModel::class.java)
    }
}