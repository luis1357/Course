package com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {

    @SerializedName("temp")
    @Expose
    var temp: Double? = null
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null
    @SerializedName("pressure")
    @Expose
    var pressure: Float? = null
    @SerializedName("sea_level")
    @Expose
    var seaLevel: Double? = null
    @SerializedName("grnd_level")
    @Expose
    var grndLevel: Float? = null
    @SerializedName("humidity")
    @Expose
    var humidity: Float? = null
    @SerializedName("temp_kf")
    @Expose
    var tempKf: Float? = null

}
