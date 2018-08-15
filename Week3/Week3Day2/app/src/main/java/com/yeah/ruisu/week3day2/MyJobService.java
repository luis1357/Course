package com.yeah.ruisu.week3day2;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.IBinder;
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

        String PName = bundle.getString("PName");
        String PAge = bundle.getString("PAge");
        String PGender = bundle.getString("PGender");
        String PNation = bundle.getString("PNation");

        Intent intent = new Intent(getApplicationContext(), MyIntentService.class);

        intent.putExtra("PName", PName);
        intent.putExtra("PAge", PAge);
        intent.putExtra("PGender", PGender);
        intent.putExtra("PNation", PNation);

        getApplicationContext().startService(intent);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters)
    {
        return false;
    }
}
