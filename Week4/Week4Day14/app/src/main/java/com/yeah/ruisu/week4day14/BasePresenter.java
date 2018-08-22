package com.yeah.ruisu.week4day14;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void removeView();
}
