package com.yeah.ruisu.realvolvocodingchallenge.di.application

import com.yeah.ruisu.realvolvocodingchallenge.utils.SingleEventLiveData
import dagger.Module
import dagger.Provides

@Module
class LiveDataModule
{
    @Provides
    fun getSingleEventLiveData(): SingleEventLiveData<Boolean>
    {
        return SingleEventLiveData()
    }
}