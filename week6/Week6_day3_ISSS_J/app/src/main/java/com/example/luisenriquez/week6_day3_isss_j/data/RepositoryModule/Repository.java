package com.example.luisenriquez.week6_day3_isss_j.data.RepositoryModule;

import android.arch.lifecycle.MutableLiveData;

import com.example.luisenriquez.week6_day3_isss_j.data.remote.models.ISSResponse;

public interface Repository
{
    MutableLiveData<ISSResponse> ISS_RESPONSE_MUTABLE_LIVE_DATA = null;

    void getISSResponse();
}
