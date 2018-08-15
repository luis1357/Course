package com.yeah.ruisu.week3day2;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent)
    {
        PersistableBundle persistableBundle = new PersistableBundle();

        persistableBundle.putString("PName",
                intent.getStringExtra("PName"));
        persistableBundle.putString("PAge",
                intent.getStringExtra("PAge"));
        persistableBundle.putString("PGender",
                intent.getStringExtra("PGender"));
        persistableBundle.putString("PNation",
                intent.getStringExtra("PNation"));

        @SuppressLint("JobSchedulerService")
        ComponentName componentName = new ComponentName(context, MyJobService.class);

        JobInfo.Builder jonInfo = new JobInfo.Builder(0, componentName);

        jonInfo.setOverrideDeadline(3000);
        jonInfo.setExtras(persistableBundle);

        JobScheduler jobScheduler = (JobScheduler)
                context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        assert jobScheduler != null;
        jobScheduler.schedule(jonInfo.build());
    }
}
