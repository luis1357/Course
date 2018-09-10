package com.yeah.ruisu.week3day2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyScndReceiver extends BroadcastReceiver
{
    public static final String TAG = "MyManifestReceiver";

    private DatabaseHelper MyDataHelper;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        /* We create the database helper. */
        MyDataHelper = new DatabaseHelper(context);

        Log.d(TAG, "onReceive: ");

        String PName = intent.getStringExtra("PName");
        String PAge = intent.getStringExtra("PAge");
        String PGender = intent.getStringExtra("PGender");
        String PNation = intent.getStringExtra("PNation");

        MyDataHelper.InsertData(PName, PAge,
                                PGender, PNation);


        String CustmMsg = PName + " " +
                PAge + " " +
                PGender + " " +
                PNation;

        Toast.makeText(context, CustmMsg, Toast.LENGTH_LONG).show();

    }
}
