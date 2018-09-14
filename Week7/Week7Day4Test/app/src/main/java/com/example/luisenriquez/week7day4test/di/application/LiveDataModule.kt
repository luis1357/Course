package com.example.luisenriquez.week7day4test.di.application

import com.example.luisenriquez.week7day4test.utils.SingleEventLiveData
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