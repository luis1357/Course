package com.example.admin.deltachallenge.di.fragment

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.example.admin.deltachallenge.di.factories.MainViewModelFactory
import com.example.admin.deltachallenge.ui.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun providesMainViewModel(mainViewModelFactory: MainViewModelFactory): MainViewModel {
        return ViewModelProviders.of(fragment, mainViewModelFactory).get(MainViewModel::class.java)
    }
}