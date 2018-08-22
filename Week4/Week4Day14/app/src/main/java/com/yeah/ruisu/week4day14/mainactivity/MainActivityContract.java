package com.yeah.ruisu.week4day14.mainactivity;

import android.content.Context;
import android.view.View;

import com.yeah.ruisu.week4day14.BasePresenter;
import com.yeah.ruisu.week4day14.BaseView;

public interface MainActivityContract {

    interface View extends BaseView
    {
        void FillUserProfile(String Name, String Location,
                             String Hirable, String RepositoriesN,
                             String PicUrl);

    }

    interface Presenter extends BasePresenter<View> {


        void setContext(Context context);

        void ShowUserName(String UserName);

        void start();

        void stop();

        void pause();

        void resume();

    }
}
