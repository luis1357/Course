package com.yeah.ruisu.week4day14.reposactivity;

import android.content.Context;
import android.content.Intent;

import com.yeah.ruisu.week4day14.Repos.RepoUserName;
import com.yeah.ruisu.week4day14.Repos.model.UserData;
import com.yeah.ruisu.week4day14.mainactivity.MainActivityContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ReposPresenter
        implements ReposContract.Presenter
{
    private static final String TAG = "ReposTag: ";
    private ReposContract.View view;
    private Context context;

    @Override
    public void setContext(Context context)
    {
        this.context = context;
    }

    @Override
    public void start()
    {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause()
    {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void resume()
    {
        EventBus.getDefault().register(this);
    }

    @Override
    public void GetUserRepos(String UserName)
    {
        RepoUserName repoUserName = new RepoUserName(context);

        repoUserName.GetUserRepos(UserName);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserData data)
    {
    }


    @Override
    public void attachView(ReposContract.View view)
    {
        this.view = view;
    }

    @Override
    public void removeView()
    {
        this.view = null;
    }

}
