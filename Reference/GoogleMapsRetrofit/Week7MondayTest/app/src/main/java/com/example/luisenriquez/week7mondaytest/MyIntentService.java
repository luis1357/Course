package com.example.luisenriquez.week7mondaytest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static com.example.luisenriquez.week7mondaytest.MainActivity.TAG;

public class MyIntentService extends IntentService
{

    public static Listener listener_obj;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name)
    {
        super(name);
    }

    public MyIntentService()
    {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId)
    {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {

        Log.d(TAG, "onHandleIntent: ");

        Intent newIntent = new Intent();

        assert intent != null;
        String tvValue = intent.getStringExtra("value");

        int intValue = Integer.parseInt(tvValue) + 1;


        newIntent.putExtra("value", String.valueOf(intValue));

        newIntent.setAction("duck");

        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(newIntent);

    }
}
