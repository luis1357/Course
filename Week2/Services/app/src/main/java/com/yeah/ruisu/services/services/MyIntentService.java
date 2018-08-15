package com.yeah.ruisu.services.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Objects;

public class MyIntentService extends IntentService
{
    public static final String TAG = "MyIntentServiceTAG: ";

    public MyIntentService()
    {
        super("MyIntentService");
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        assert intent != null;
        switch (Objects.requireNonNull(intent.getAction()))
        {
            case "getRepo":
                Log.d(TAG, "onHandleIntent: " +
                        intent.getStringExtra("data") +
                        Thread.currentThread());
                break;

            case "getProfile":
                Log.d(TAG, "onHandleIntent: " +
                        intent.getStringExtra("data") +
                        Thread.currentThread());
                break;
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
