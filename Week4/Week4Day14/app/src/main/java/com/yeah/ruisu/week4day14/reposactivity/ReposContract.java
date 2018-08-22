package com.yeah.ruisu.week4day14.reposactivity;

import android.content.Context;

import com.yeah.ruisu.week4day14.BasePresenter;
import com.yeah.ruisu.week4day14.BaseView;
import com.yeah.ruisu.week4day14.Repos.Repo;
import com.yeah.ruisu.week4day14.mainactivity.MainActivityContract;

public interface ReposContract
{
    interface View extends BaseView
    {
    }

    interface Presenter extends BasePresenter<ReposContract.View> {


        void setContext(Context context);

        void start();

        void stop();

        void pause();

        void resume();

        void GetUserRepos(String UserName);
    }
}
