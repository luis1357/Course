package com.yeah.ruisu.broadcastreceivers;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService
{
    public static final String TAG = "MyIntentService";

    public MyIntentService()
    {
        super("Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        Log.d(TAG, "onHandleIntent: ");

        Intent newIntent = new Intent();
        String message = intent.getStringExtra("data");
        newIntent.putExtra("data", message);
        newIntent.setAction(Constants.MY_SERVICE_BROADCAST);
        newIntent.setPackage(getPackageName());

        sendBroadcast(newIntent);
    }
}
