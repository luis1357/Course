package com.yeah.ruisu.week4day13.base;

import android.content.Context;
import android.support.annotation.NonNull;

public class BasePresenter
{
    private Context mContext;

    public void subscribe(@NonNull Context context)
    {
        this.mContext = context;
    }

    public boolean isSubscribed ()
    {
        return mContext != null;
    }

}