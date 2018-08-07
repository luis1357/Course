package com.yeah.ruisu.w2daily1;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;

public class MyRunnable implements Runnable
{
    private Handler MyHandler = new Handler(Looper.getMainLooper());
    private ProgressBar MyProgressBar;
    private int StartDelay;
    int i;
    private String TAG = "Runnable: ";

    public MyRunnable(ProgressBar InProgressBar, int InputDelay)
    {
        this.MyProgressBar = InProgressBar;
        this.StartDelay = InputDelay;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread());

        for(i = 0; i < 100; i++)
        {
            try
            {
                Thread.sleep(StartDelay);
                System.out.println(String.valueOf(i));

                Log.d(TAG, "Run: " + i);
                MyHandler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        MyProgressBar.setProgress(i);
                    }
                }, StartDelay);

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
