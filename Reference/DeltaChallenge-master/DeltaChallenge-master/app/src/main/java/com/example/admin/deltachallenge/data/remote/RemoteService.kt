package com.example.admin.deltachallenge.data.remote

import com.example.admin.deltachallenge.data.models.NumbersResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface RemoteService {
    @GET("API/jsonI.php?length=40&type=uint8")
    fun getRandomNumbers(): Deferred<NumbersResponse>

}