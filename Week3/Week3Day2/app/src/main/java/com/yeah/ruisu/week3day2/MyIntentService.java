package com.yeah.ruisu.week3day2;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends IntentService
{
    public static final String TAG = "MyIntentService";

    public MyIntentService()
    {
        super("Service");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        Log.d(TAG, "onStart: ");
        super.onStart(intent, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        Intent newIntent = new Intent();

        assert intent != null;
        String PName = intent.getStringExtra("PName");
        String PAge = intent.getStringExtra("PAge");
        String PGender = intent.getStringExtra("PGender");
        String PNation = intent.getStringExtra("PNation");

        newIntent.setAction(Constants.MY_SERVICE_BROADCAST);

        newIntent.putExtra("PName", PName);
        newIntent.putExtra("PAge", PAge);
        newIntent.putExtra("PGender", PGender);
        newIntent.putExtra("PNation", PNation);

        //newIntent.setPackage(getPackageName());

        sendBroadcast(newIntent);

        String CustmMsg = PName + " " +
                PAge + " " +
                PGender + " " +
                PNation;

        Log.d(TAG, "onHandleIntent: " + CustmMsg);
    }
}
