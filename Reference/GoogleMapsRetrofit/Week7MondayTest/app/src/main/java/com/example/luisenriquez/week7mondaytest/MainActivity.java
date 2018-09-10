package com.example.luisenriquez.week7mondaytest;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity
{

    public static final String TAG = "MainActivity:";

    TextView tvTextView;

    private BroadcastReceiver statusReceiver;
    private IntentFilter mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindControls();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                                                                    new IntentFilter("duck"));
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        if(mIntent != null)
        {
            unregisterReceiver(statusReceiver);
            mIntent = null;
        }
    }



    public void BindControls()
    {
        tvTextView = findViewById(R.id.tvTextView);
    }

    public void UpdateTextViewValue(View view)
    {
        Log.d(TAG, "UpdateTextViewValue: ");

        UpdateWithService();
    }

    public void UpdateWithService()
    {
        String tvValue = tvTextView.getText().toString();

        Intent intent = new Intent(MainActivity.this, MyIntentService.class);

        intent.putExtra("value", tvValue);

        startService(intent);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String tvValue =  intent.getStringExtra("value");

            tvTextView.setText(tvValue);
        }
    };

}
