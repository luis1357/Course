package com.example.admin.deltachallenge.data.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.admin.deltachallenge.data.models.NumbersResponse
import com.example.admin.deltachallenge.data.remote.RemoteServiceHelper
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException

class RepositoryImpl(private val remoteServiceHelper: RemoteServiceHelper) : Repository {

    override val averageLiveData: MutableLiveData<Double> = MutableLiveData()
    override val randomNumbersLiveData: MutableLiveData<NumbersResponse> = MutableLiveData()

    override fun getRandomNumbers(): MutableLiveData<NumbersResponse> {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            if (throwable is HttpException) {
                //TODO implement exception handling
                Log.d("Main", "In HttpException")

            } else {
                //TODO implement exception handling
                Log.d("Main", "In other error", throwable.fillInStackTrace())
            }
        }

        launch(exceptionHandler) {
            val numbersResponse = remoteServiceHelper.getRandomNumbers().await()
            averageLiveData.postValue(numbersResponse.data.average())
            randomNumbersLiveData.postValue(numbersResponse)
        }
        return randomNumbersLiveData
    }
}