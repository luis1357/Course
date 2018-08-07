package com.yeah.ruisu.w2daily1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private static final String TAG = "MainActivity";

    ProgressBar PrgssBr1, PrgssBr2, PrgssBr3, PrgssBr4;
    int Dly1, Dly2, Dly3, Dly4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrgssBr1 = findViewById(R.id.progressBar1);
        PrgssBr2 = findViewById(R.id.progressBar2);
        PrgssBr3 = findViewById(R.id.progressBar3);
        PrgssBr4 = findViewById(R.id.progressBar4);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        //EventBus.getDefault().unregister(this);
    }

    public int GetRandomNumber()
    {
        Random r = new Random();
        int LowerLimit = 10;
        int UpperLimit = 2000;
        int Result;

        Result = r.nextInt(UpperLimit - LowerLimit) + LowerLimit;

        return  Result;
    }

    public void DoMagic(View view)
    {
        Dly1 = GetRandomNumber();
        Dly2 = GetRandomNumber();
        Dly3 = GetRandomNumber();
        Dly4 = GetRandomNumber();

        MyRunnable Run1 = new MyRunnable(PrgssBr1, Dly1);
        MyRunnable Run2 = new MyRunnable(PrgssBr2, Dly2);
        MyRunnable Run3 = new MyRunnable(PrgssBr3, Dly3);
        MyRunnable Run4 = new MyRunnable(PrgssBr4, Dly4);

        Thread MyThread1 = new Thread(Run1);
        Thread MyThread2 = new Thread(Run2);
        Thread MyThread3 = new Thread(Run3);
        Thread MyThread4 = new Thread(Run4);

        MyThread1.start();
        MyThread2.start();
        MyThread3.start();
        MyThread4.start();
    }

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }
}
