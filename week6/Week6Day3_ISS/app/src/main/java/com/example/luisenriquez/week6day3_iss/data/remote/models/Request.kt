package com.example.luisenriquez.week6day3_iss.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Request {

    @SerializedName("altitude")
    @Expose
    var altitude: Int? = null
    @SerializedName("datetime")
    @Expose
    var datetime: Int? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
    @SerializedName("passes")
    @Expose
    var passes: Int? = null

}
