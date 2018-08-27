package com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class WeatherApiResponse {

    @SerializedName("cod")
    @Expose
    var cod: String? = null
    @SerializedName("message")
    @Expose
    var message: Double? = null
    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null
    @SerializedName("city")
    @Expose
    var city: City? = null
    @SerializedName("list")
    @Expose
    var apiList: List<ApiList>? = null
}
