package com.example.admin.multithreading;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class TestThread extends Thread
{
    Handler MyHandler = new Handler(Looper.getMainLooper());

    TextView tvTesting;
    int i;


    public TestThread(TextView IntvTesting)
    {
        this.tvTesting = IntvTesting;
    }

    @Override
    public void run()
    {
        super.run();

        System.out.println(Thread.currentThread());

        for (i = 0; i < 10; i++)
        {
            try
            {
                Thread.sleep(1000);
                System.out.println(i);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            MyHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    tvTesting.setText(String.valueOf(i));
                }
            });

            EventBus.getDefault().post(new MessageEvent(String.valueOf(i)));

        }
    }
}
