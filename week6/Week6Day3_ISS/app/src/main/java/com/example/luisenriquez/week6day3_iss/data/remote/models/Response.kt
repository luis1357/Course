package com.example.luisenriquez.week6day3_iss.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("duration")
    @Expose
    var duration: Int? = null
    @SerializedName("risetime")
    @Expose
    var risetime: Int? = null

}
