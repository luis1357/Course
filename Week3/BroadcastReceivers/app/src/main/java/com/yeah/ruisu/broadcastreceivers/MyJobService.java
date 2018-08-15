package com.yeah.ruisu.broadcastreceivers;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.PersistableBundle;
import android.util.Log;

public class MyJobService extends JobService
{

    public static final String TAG = "MyJobService: ";

    @Override
    public boolean onStartJob(JobParameters jobParameters)
    {
        Log.d(TAG, "onStartJob: ");
        PersistableBundle bundle = jobParameters.getExtras();
        String message = bundle.getString("data");
        Intent intent = new Intent(getApplicationContext(), MyIntentService.class);

        intent.putExtra("data", message);
        getApplicationContext().startService(intent);


        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters)
    {
        return false;
    }
}
