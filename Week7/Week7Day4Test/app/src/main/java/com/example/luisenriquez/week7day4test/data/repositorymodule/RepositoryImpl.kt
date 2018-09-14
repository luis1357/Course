package com.example.luisenriquez.week7day4test.data.repositorymodule

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.luisenriquez.week7day4test.data.remote.RemoteServiceHelper
import com.example.luisenriquez.week7day4test.data.remote.model.NumbersResponse
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException

class RepositoryImpl (private val remoteServiceHelper: RemoteServiceHelper) : Repository
{
    override val numberResponseLiveData: MutableLiveData<NumbersResponse> = MutableLiveData()

    override fun getRandomNumbers()
    {
        val exceptionHandler = CoroutineExceptionHandler {_,
                                                         throwable ->
            if (throwable is HttpException)
            {
                Log.d("main", "In HttpException")
            }
            else
            {
                Log.d("Main", "Other error: ", throwable.fillInStackTrace())
            }
        }

        launch (exceptionHandler)
        {
            val metaNumbersResponse = remoteServiceHelper.getRandomNumbers()
                                                            .await()
            numberResponseLiveData.postValue(metaNumbersResponse)
        }
    }
}