package com.example.luisenriquez.week7day4test.data.remote

import com.example.luisenriquez.week7day4test.data.remote.model.NumbersResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET

interface RemoteService
{
    @GET ("API/jsonI.php?length=40&type=uint8")
    fun getRandomNumbers(): Deferred<NumbersResponse>
}