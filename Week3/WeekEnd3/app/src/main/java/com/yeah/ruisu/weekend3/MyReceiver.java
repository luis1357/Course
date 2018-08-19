package com.yeah.ruisu.weekend3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String MyMessage = intent.getStringExtra("data");

        Intent MyIntent = new Intent(context, MyIntentService.class);

        MyIntent.putExtra("data", MyMessage);

        context.startService(MyIntent);

    }
}
