package com.yeah.ruisu.services.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyScheduleService extends Service 
{
    private static final String TAG = "MySchServiceTAG";
    
    public MyScheduleService() 
    {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) 
    {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
