package com.yeah.ruisu.mybroadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class BrowserReceiver extends BroadcastReceiver
{


    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, "onReceive: ");

        Uri uri = intent.getData();

        Intent newIntent = new Intent(Intent.ACTION_VIEW, uri);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(newIntent);
    }
}
