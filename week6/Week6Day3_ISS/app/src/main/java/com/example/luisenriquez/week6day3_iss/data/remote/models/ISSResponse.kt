package com.example.luisenriquez.week6day3_iss.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ISSResponse {

    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("request")
    @Expose
    var request: Request? = null
    @SerializedName("response")
    @Expose
    var response: List<Response>? = null

}
