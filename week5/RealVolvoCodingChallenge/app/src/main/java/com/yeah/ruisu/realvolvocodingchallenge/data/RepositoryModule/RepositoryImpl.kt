package com.yeah.ruisu.realvolvocodingchallenge.data.RepositoryModule

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.yeah.ruisu.realvolvocodingchallenge.data.remote.RemoteServiceHelper
import com.yeah.ruisu.realvolvocodingchallenge.data.remote.models.ForecastDatum
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException

class RepositoryImpl (private val remoteServiceHelper: RemoteServiceHelper) : Repository
{
    override val weatherForecastLiveData: MutableLiveData<List<ForecastDatum>> = MutableLiveData()

    override fun getWeatherForecast(WOEID: String)
    {
        val exceptionHandler = CoroutineExceptionHandler {_,
                                                         throwable ->
            if (throwable is HttpException)
            {
                Log.d("main", "In HttpException")
            }
            else
            {
                Log.d("Main", "Other error", throwable.fillInStackTrace())
            }
        }

        launch (exceptionHandler)
        {
            val metaWeatherResponse = remoteServiceHelper.getWeatherForecast(WOEID)
                                                            .await()
            weatherForecastLiveData.postValue(metaWeatherResponse)
        }
    }
}