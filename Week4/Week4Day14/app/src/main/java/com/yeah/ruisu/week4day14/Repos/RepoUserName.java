package com.yeah.ruisu.week4day14.Repos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;


import com.yeah.ruisu.week4day14.Repos.Remote.RemoteServiceHelper;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.schedulers.Schedulers;

public class RepoUserName implements Repo
{
    private static final String TAG = "RepoUserName: ";

    Context context;

    Handler handler;

    public RepoUserName(Context context)
    {
        this.context = context;

        handler = new Handler();
    }

    @SuppressLint("CheckResult")
    @Override
    public void ShowUserName(String UserName)
    {
        RemoteServiceHelper MyRmtSrvcHlpr = RemoteServiceHelper.getINSTANCE();

        MyRmtSrvcHlpr.getUserData(UserName)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(data ->
                {
                    runOnUiThread(() ->
                    {
                        Log.d(TAG, "ShowUserName: " +
                                data.getName());

                        EventBus.getDefault().post(data);
                    });
                })
                .subscribe(data ->
                        {
                            //Log.d(TAG, "makeCall: " +
                              //      data.getLocation());
                        },
                        Throwable::printStackTrace);
    }

    @SuppressLint("CheckResult")
    @Override
    public void GetUserRepos(String UserName)
    {
        RemoteServiceHelper MyRmtSrvcHlpr = RemoteServiceHelper.getINSTANCE();

        MyRmtSrvcHlpr.getUserReposData(UserName)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(data ->
                {
                    runOnUiThread(() ->
                    {
                        Log.d(TAG, "GetUserRepos: " +
                                data.get(0).getName());

                        EventBus.getDefault().post(data);
                    });
                })
                .subscribe(data ->
                        {
                            //Log.d(TAG, "makeCall: " +
                            //      data.getLocation());
                        },
                        Throwable::printStackTrace);
    }


    private void runOnUiThread(Runnable runnable)
    {
        handler.post(runnable);
    }
}
