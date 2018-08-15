package com.yeah.ruisu.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyManifestDeclaredReceiver extends BroadcastReceiver
{

    public static final String TAG = "MyManifestReceiver";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, "onReceive: ");
        String message = intent.getStringExtra("data");
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
