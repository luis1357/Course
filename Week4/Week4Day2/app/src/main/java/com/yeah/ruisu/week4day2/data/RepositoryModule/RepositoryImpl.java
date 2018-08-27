package com.yeah.ruisu.week4day2.data.RepositoryModule;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.yeah.ruisu.week4day2.data.remote.RemoteServiceHelper;
import com.yeah.ruisu.week4day2.data.remote.models.UserData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository
{
    MutableLiveData<UserData> userDataLiveData = new MutableLiveData<>();
    RemoteServiceHelper MyRemoteServiceHelper;

    public RepositoryImpl(RemoteServiceHelper InRemoteServiceHelper)
    {
        this.MyRemoteServiceHelper = InRemoteServiceHelper;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getUserData(String userName)
    {
        MyRemoteServiceHelper.getUserData(userName)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(gitHubApiResponse ->
                                        userDataLiveData.setValue(gitHubApiResponse),
                                        Throwable::printStackTrace);
    }

    @Override
    public void getUserReposData(String userName) {

    }


    public MutableLiveData<UserData> getUserDataLiveData() {
        return userDataLiveData;
    }
}
