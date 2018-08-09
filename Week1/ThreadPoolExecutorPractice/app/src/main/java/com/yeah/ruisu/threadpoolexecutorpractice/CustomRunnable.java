package com.yeah.ruisu.threadpoolexecutorpractice;

import android.os.Bundle;
import android.os.Message;

import java.lang.ref.WeakReference;

public class CustomRunnable implements Runnable
{
    private WeakReference<CustomThreadPoolManager> MyCstmThrdPlMngrWkRfrnc;

    public CustomRunnable (CustomThreadPoolManager InCstmThrdPlMngrWkRfrnc)
    {
        MyCstmThrdPlMngrWkRfrnc = new WeakReference<>(InCstmThrdPlMngrWkRfrnc);
    }

    @Override
    public void run ()
    {
        try
        {
            for (int i = 1; i < 5; i++)
            {
                Thread.sleep(1000);
                String MyMessage = "Random int: " + i + "\nThread " +
                                    String.valueOf(Thread.currentThread().getId()) +
                                    "\nCompleted";

                Bundle MyBundle = new Bundle();
                MyBundle.putString("message", MyMessage);
                MyBundle.putInt("int", i);
                Message Message1 = new Message();
                Message1.setData(MyBundle);
                Message1.what = (int) (Thread.currentThread().getId() % 4 + 1);
                MyCstmThrdPlMngrWkRfrnc.get().PostUiThread(Message1);
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
