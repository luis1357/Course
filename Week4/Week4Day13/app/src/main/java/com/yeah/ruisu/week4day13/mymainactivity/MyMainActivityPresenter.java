package com.yeah.ruisu.week4day13.mymainactivity;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yeah.ruisu.week4day13.base.BasePresenter;

public class MyMainActivityPresenter extends BasePresenter implements MyMainActivityContract.Presenter {

    private MyMainActivityContract.View mView;

    private Context mContext;

    public MyMainActivityPresenter(@NonNull Context context, @NonNull MyMainActivityContract.View view) {
        this.mView = view;
        this.mContext = context;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
