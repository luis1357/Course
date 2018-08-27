package com.yeah.ruisu.week4day2.data.RepositoryModule;

import android.arch.lifecycle.MutableLiveData;

public interface Repository
{
    void getUserData(String userName);

    void getUserReposData(String userName);
}
