package com.yeah.ruisu.services.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class MyJobService extends JobService
{
    @Override
    public boolean onStartJob(JobParameters jobParameters)
    {
        Log.d(TAG, "onStartJob: ");
        Intent MyIntent = new Intent(getApplicationContext(), MyScheduleService.class);
        getApplication().startService(MyIntent);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters)
    {
        return false;
    }
}
