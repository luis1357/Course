package com.yeah.ruisu.week4day14.mainactivity;

import android.content.Context;

import com.yeah.ruisu.week4day14.Repos.RepoUserName;
import com.yeah.ruisu.week4day14.Repos.model.UserData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivityPresenter
        implements MainActivityContract.Presenter
{

    private static final String TAG = "MainPresenterTag";
    private MainActivityContract.View view;
    private Context context;


    @Override
    public void start() {

    }

    @Override
    public void stop()
    {

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UserData data)
    {

        String UserName = data.getName();
        String UserLocation = data.getLocation();
        String UserHirable = data.getHireable().toString();
        String UserRepoNmbr = data.getPublicRepos().toString();
        String UserPicUrl = data.getAvatarUrl();

        view.FillUserProfile(UserName, UserLocation,
                                UserHirable, UserRepoNmbr,
                                UserPicUrl);
    }


    @Override
    public void setContext(Context context)
    {
        this.context = context;
    }

    @Override
    public void ShowUserName(String UserName)
    {
        RepoUserName repoUserName = new RepoUserName(context);

        repoUserName.ShowUserName(UserName);
    }


    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }


}
