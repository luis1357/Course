package com.yeah.ruisu.week4day1.presenter.loader;

import android.support.annotation.NonNull;

import com.yeah.ruisu.week4day1.presenter.BasePresenter;

/**
 * Factory to implement to create a presenter
 */
public interface PresenterFactory<T extends BasePresenter> {
    @NonNull
    T create();
}
