package com.yeah.ruisu.week4day13.base;

public interface BaseContract
{
    interface View<T extends Presenter>
    {
        void setPresenter(T presenter);
    }

    interface Presenter
    {
        void start();

        void stop();
    }
}