package com.yeah.ruisu.weekend5_walmartchallenge.data.repositorymodule;

import android.arch.lifecycle.MutableLiveData;

import com.yeah.ruisu.weekend5_walmartchallenge.data.remote.models.Item;

public interface Repository
{
    MutableLiveData<Item> walmartSearchLiveData = null;

    void getSearchResults(String srchItm);
}
