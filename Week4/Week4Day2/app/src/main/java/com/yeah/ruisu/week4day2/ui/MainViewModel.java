package com.yeah.ruisu.week4day2.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.yeah.ruisu.week4day2.AppController;
import com.yeah.ruisu.week4day2.data.RepositoryModule.Repository;
import com.yeah.ruisu.week4day2.data.RepositoryModule.RepositoryImpl;
import com.yeah.ruisu.week4day2.data.remote.models.UserData;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainViewModel extends AndroidViewModel
{
    @Inject
    Repository myRepo;

    private MutableLiveData<String> userNameT = new MutableLiveData<>();
    private MutableLiveData<String> userReposN = new MutableLiveData<>();
    private MutableLiveData<String> userHirable = new MutableLiveData<>();
    private MutableLiveData<String> userLocation = new MutableLiveData<>();


    public MainViewModel(@NonNull Application application)
    {
        super(application);

       ((AppController) application).getAppComponent().inject(this);
    }



    public LiveData<UserData> getUserData(String userName)
    {
        myRepo.getUserData(userName);

        return Transformations.map(((RepositoryImpl) myRepo)
                                    .getUserDataLiveData(),
                input ->
                {
                    userNameT.setValue(input.getName());
                    userReposN.setValue(input.getPublicRepos().toString());
                    userHirable.setValue(input.getHireable().toString());
                    userLocation.setValue(input.getLocation());

                    return input;
                });
    }

    public MutableLiveData<String> getUserNameT()
    {
        return userNameT;
    }

    public void setUserNameT(MutableLiveData<String> userNameT)
    {
        this.userNameT = userNameT;
    }

    public MutableLiveData<String> getUserReposN() {
        return userReposN;
    }

    public void setUserReposN(MutableLiveData<String> userReposN) {
        this.userReposN = userReposN;
    }

    public MutableLiveData<String> getUserHirable() {
        return userHirable;
    }

    public void setUserHirable(MutableLiveData<String> userHirable) {
        this.userHirable = userHirable;
    }

    public MutableLiveData<String> getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(MutableLiveData<String> userLocation) {
        this.userLocation = userLocation;
    }
}
