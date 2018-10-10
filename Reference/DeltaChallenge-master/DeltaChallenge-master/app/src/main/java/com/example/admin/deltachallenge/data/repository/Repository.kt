package com.example.admin.deltachallenge.data.repository

import android.arch.lifecycle.MutableLiveData
import com.example.admin.deltachallenge.data.models.NumbersResponse

interface Repository {

    val randomNumbersLiveData: MutableLiveData<NumbersResponse>

    val averageLiveData: MutableLiveData<Double>

    fun getRandomNumbers(): MutableLiveData<NumbersResponse>
}