package com.example.luisenriquez.week7day4test.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class NumbersResponse
{
    @SerializedName("type")
    @Expose
    private var type: String? = null
    @SerializedName("length")
    @Expose
    private var length: Int? = null
    @SerializedName("data")
    @Expose
    private var data: List<Int>? = null
    @SerializedName("success")
    @Expose
    private var success: Boolean? = null

    fun getType(): String? {
        return type
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getLength(): Int? {
        return length
    }

    fun setLength(length: Int?) {
        this.length = length
    }

    fun getData(): List<Int>? {
        return data
    }

    fun setData(data: List<Int>) {
        this.data = data
    }

    fun getSuccess(): Boolean? {
        return success
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

}