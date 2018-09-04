package com.yeah.ruisu.weekend5_walmartchallenge.data.repositorymodule;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.yeah.ruisu.weekend5_walmartchallenge.data.remote.RemoteServiceHelper;
import com.yeah.ruisu.weekend5_walmartchallenge.data.repositorymodule.Repository;
import com.yeah.ruisu.weekend5_walmartchallenge.data.remote.models.Item;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository 
{
    MutableLiveData<List<Item>> walmartSearchLiveData = new MutableLiveData<>();
    RemoteServiceHelper myRemoteServiceHelper;
    
    public RepositoryImpl(RemoteServiceHelper inRmtSrvcHlpr)
    {
        this.myRemoteServiceHelper = inRmtSrvcHlpr;
    }
    
    @SuppressLint("CheckResult")
    @Override
    public void getSearchResults(String srchItm) 
    {
        myRemoteServiceHelper.getSearchResults(srchItm)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(walmartAPIResponse ->
                                        walmartSearchLiveData.setValue(
                                                walmartAPIResponse.getItems()
                                        ),
                                        Throwable::printStackTrace);
    }

    public MutableLiveData<List<Item>> getWalmartSearchLiveData()
    {
        return walmartSearchLiveData;
    }
}
