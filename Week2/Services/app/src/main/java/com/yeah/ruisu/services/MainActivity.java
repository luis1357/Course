package com.yeah.ruisu.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yeah.ruisu.services.services.MyBoundService;
import com.yeah.ruisu.services.services.MyIntentService;
import com.yeah.ruisu.services.services.MyJobService;
import com.yeah.ruisu.services.services.MyNormalService;

public class MainActivity extends AppCompatActivity
{
    private  static final String TAG = "MainActivity";
    private MyBoundService MyBndSrvc;
    private boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartService(View view)
    {
        Intent BoundIntent = new Intent(this, MyBoundService.class);
        Intent IntIntent = new Intent(this, MyIntentService.class);
        Intent NormalIntent = new Intent(this, MyNormalService.class);

        switch (view.getId())
        {
            case R.id.btnScheduleService:
                ComponentName MySrvcComponent = new ComponentName(this, MyJobService.class);
                JobInfo.Builder MyJobInfo = new JobInfo.Builder(0, MySrvcComponent);

                MyJobInfo.setMinimumLatency(1000);
                MyJobInfo.setOverrideDeadline(1000);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    JobScheduler MyJobSchdlr = (JobScheduler)
                                                getSystemService(Context.JOB_SCHEDULER_SERVICE);
                    MyJobSchdlr.schedule(MyJobInfo.build());
                }

                break;

            case R.id.btnBindService:
                bindService(BoundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;

            case R.id.btnStartIntentService:
                IntIntent.putExtra("data",
                                    "this is an intent service repo");
                IntIntent.setAction("getRepo");
                startService(IntIntent);
                break;

            case R.id.btnUnBindService:
                if(isBound)
                {
                    unbindService(serviceConnection);
                    isBound = false;
                }
                break;

            case R.id.btnStartNormalService:
                NormalIntent.putExtra("data",
                                        "This is a normal service");
                startService(NormalIntent);
                break;

            case R.id.btnStopNormalService:
                NormalIntent.putExtra("data",
                                        "Stopping Normal Service");
                stopService(NormalIntent);
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder iBinder)
        {
            MyBoundService.MyBinder MyBndr = (MyBoundService.MyBinder) iBinder;

            MyBndSrvc = MyBndr.getService();

            Log.d(TAG, "onServiceConnected: " + MyBndSrvc.getRandomData());
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;
        }
    };
}
