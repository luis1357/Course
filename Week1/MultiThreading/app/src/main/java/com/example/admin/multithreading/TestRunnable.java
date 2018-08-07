package com.example.admin.multithreading;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

public class TestRunnable implements Runnable {

    Handler MyHandler = new Handler(Looper.getMainLooper());
    TextView MyTextView;
    int i;
    String TAG = "Runnable: ";

    public TestRunnable(TextView InTextView)
    {
        this.MyTextView = InTextView;
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread());

        for(i = 0; i < 5; i++)
        {
            try
            {
                Thread.sleep(1000);
                System.out.println(String.valueOf(i));

                Log.d(TAG, "run " + i);
                MyHandler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        MyTextView.setText(String.valueOf(i));
                    }
                }, 2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
