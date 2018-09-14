package com.example.luisenriquez.week7day4test.data.repositorymodule

import android.arch.lifecycle.MutableLiveData
import com.example.luisenriquez.week7day4test.data.remote.model.NumbersResponse

interface Repository
{
    val numberResponseLiveData : MutableLiveData<NumbersResponse>

    fun getRandomNumbers()
}