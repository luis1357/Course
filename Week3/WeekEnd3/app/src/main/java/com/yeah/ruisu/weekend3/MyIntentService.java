package com.yeah.ruisu.weekend3;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.yeah.ruisu.weekend3.data.remote.RemoteServiceHelper;
import com.yeah.ruisu.weekend3.models.BooksData;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class MyIntentService extends IntentService
{
    public static final String TAG = "MyIntentService";

    Handler handler;

    public MyIntentService() {
        super("MyIntentService");
    }

    @SuppressLint("InflateParams")
    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, startId, startId);
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        assert intent != null;
        String MyMessage = intent.getStringExtra("data");

        RequestBooks(MyMessage);

        Log.d(TAG, "onHandleIntent: " + MyMessage);
    }

    private void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    @SuppressLint("CheckResult")
    public void RequestBooks (String KeyWord)
    {
        RemoteServiceHelper MyRmtSrvcHlpr = RemoteServiceHelper.getINSTANCE();

        MyRmtSrvcHlpr.getBooksData(KeyWord)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(data ->
                {
                    runOnUiThread(() ->
                    {
                        EventBus.getDefault().post(data);
                    });
                })
                .subscribe(data ->
                        {
                            Log.d(TAG, "makeCall: " +
                                    data.getItems().toString());
                        },
                        Throwable::printStackTrace);
    }

}
