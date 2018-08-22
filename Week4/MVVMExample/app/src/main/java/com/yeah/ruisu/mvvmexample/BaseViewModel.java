package com.yeah.ruisu.mvvmexample;

import android.databinding.BaseObservable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseViewModel <T extends AppCompatActivity>
        extends BaseObservable
{
    private T activity;

    public BaseViewModel(T activity)
    {
        this.activity = activity;
    }

    public T getActivity()
    {
        return activity;
    }

    public abstract void onStart();

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onStop();

    public abstract void onDestroy();
}
