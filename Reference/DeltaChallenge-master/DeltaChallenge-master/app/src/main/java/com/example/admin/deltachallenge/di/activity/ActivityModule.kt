package com.example.admin.deltachallenge.di.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import com.example.admin.deltachallenge.R
import com.example.admin.deltachallenge.databinding.ActivityMainBinding
import com.example.admin.deltachallenge.ui.CommunicatorViewModel
import com.example.admin.deltachallenge.ui.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun providesCommunicatorViewModel(): CommunicatorViewModel {
        return ViewModelProviders.of(activity).get(CommunicatorViewModel::class.java)
    }

    @Provides
    @ActivityScope
    fun providesBindingClass(): ActivityMainBinding {
        return DataBindingUtil.setContentView(activity, R.layout.activity_main)
    }
}