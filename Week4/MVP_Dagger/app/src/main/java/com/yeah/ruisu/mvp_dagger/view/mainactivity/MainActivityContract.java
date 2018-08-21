package com.yeah.ruisu.mvp_dagger.view.mainactivity;

import android.content.Context;

import com.yeah.ruisu.mvp_dagger.BasePresenter;
import com.yeah.ruisu.mvp_dagger.BaseView;

public interface MainActivityContract
{
    interface View extends BaseView
    {
        void isPersonSaved(boolean isSaved);

        void sendPerson(String person);
    }

    interface Presenter extends BasePresenter<View>
    {
        void savePerson(String person);

        void getPerson();

        void setContext(Context context);
    }
}
