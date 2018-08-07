package com.example.admin.multithreading;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

public class MainActivity extends AppCompatActivity implements Handler.Callback
{

    private static final String TAG = "MainActivity: ";
    TextView tvTesting, tvTestTHM;
    int DlyStrt1, DlyStrt2, DlyStrt3, DlyStrt4;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTesting = findViewById(R.id.tvTesting);
        tvTestTHM = findViewById(R.id.tvThreadHandlerMessage);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent InMessageEvent)
    {
        Toast.makeText(this, InMessageEvent.getMessage(),
                        Toast.LENGTH_SHORT).show();
    }

    public void ExecuteThreads(View view)
    {
        switch(view.getId())
        {
            case R.id.btnThread:
                TestThread MyTestThread = new TestThread(tvTesting);
                MyTestThread.start();

                break;

            case R.id.btnRunnable:
                TestRunnable MyTestRunnable = new TestRunnable(tvTesting);
                Thread MyThread = new Thread(MyTestRunnable);
                MyThread.start();

                break;

            case R.id.btnAsyncTask:
                TestAsyncTask MyTestAsyncTask = new TestAsyncTask();
                MyTestAsyncTask.execute("Starting");

                break;

            case R.id.btnThreadHandlerMessage:

                /* Creating a new handler with the callback. */
                Handler MyHandler = new Handler(new Handler.Callback()
                {
                    @Override
                    public boolean handleMessage(Message MyMessage)
                    {
                        Log.d(TAG, "HandleMessage: from Handler");
                        tvTestTHM.setText(MyMessage.getData().getString("KEY_DATA"));

                        return false;
                    }

                });

                /* Using Handler. */
                TestThreadHandlerMessage MyTestThreadHandlerMessage =
                                                        new TestThreadHandlerMessage(MyHandler);
                MyTestThreadHandlerMessage.start();

                /* Create the handler by implementing the callback in the class. */
                Handler MyHandler1 = new Handler(this);

                /* Using Handler1. */
                TestThreadHandlerMessage MyTestThreadHandlerMessage1 =
                                                        new TestThreadHandlerMessage(MyHandler1);
                MyTestThreadHandlerMessage1.start();

                /* Create the handler by implementing the callback in the class. */
                Handler MyHandler2 =
                        new Handler(new com.example.admin.multithreading.CallbackHandler());

                /* Using handler 2. */
                TestThreadHandlerMessage MyTestThreadHandlerMessage2 =
                                                        new TestThreadHandlerMessage(MyHandler2);
                MyTestThreadHandlerMessage2.start();

                break;
        }
    }


    @Override
    public boolean handleMessage(Message message)
    {
        tvTestTHM.setText(message.getData().getString("KEY_DATA"));
        Log.d(TAG, "HandleMessage: from Handler1");
        return false;
    }
}
