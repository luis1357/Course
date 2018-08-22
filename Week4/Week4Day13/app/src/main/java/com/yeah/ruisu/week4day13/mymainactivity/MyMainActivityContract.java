package com.yeah.ruisu.week4day13.mymainactivity;

import com.yeah.ruisu.week4day13.base.BaseContract;

//todo create BaseContract and import to this class
public interface MyMainActivityContract {

    interface View extends BaseContract.View<Presenter> {

    }

    interface Presenter extends BaseContract.Presenter {

    }
}
