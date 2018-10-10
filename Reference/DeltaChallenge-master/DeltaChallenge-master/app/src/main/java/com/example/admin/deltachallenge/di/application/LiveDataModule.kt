package com.example.admin.deltachallenge.di.application

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides

@Module
class LiveDataModule {

    @Provides
    fun providesIntLiveData(): MutableLiveData<Int> {
        return MutableLiveData()
    }

    @Provides
    fun providesDoubleLiveData(): MutableLiveData<Double> {
        return MutableLiveData()
    }

    @Provides
    fun providesStringMediatorLiveData(): MediatorLiveData<String> {
        return MediatorLiveData()
    }
}