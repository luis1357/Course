package com.yeah.ruisu.mvp_dagger;

public interface BasePresenter <V extends BaseView>
{
    void attachView(V view);

    void removeView();
}
