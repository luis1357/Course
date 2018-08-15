package com.yeah.ruisu.broadcastreceivers;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private Button btnSendBroadcast, btnSendActionViewBroadcast;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendBroadcast = findViewById(R.id.btnSendBroadcast);
        btnSendActionViewBroadcast = findViewById(R.id.btnSendActionViewBroadcast);

        myBroadcastReceiver = new MyBroadcastReceiver();

        setUpListeners();
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.MY_BROADCAST);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }

    private void setUpListeners()
    {
        btnSendBroadcast.setOnClickListener(view -> {
            Intent intent = new Intent();

            intent.setAction(Constants.MY_BROADCAST);
            intent.putExtra("data", "this is a message");

            sendBroadcast(intent);

        });

        btnSendActionViewBroadcast.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction("potato");

            intent.setComponent(new ComponentName("com.yeah.ruisu.mybroadcastreceiver2",
                                "com.yeah.ruisu.mybroadcastreceiver2.BrowserReceiver"));

            intent.setData(Uri.parse("https://www.google.com/"));

            sendBroadcast(intent);

//            Toast.makeText(this, "Hekki world", Toast.LENGTH_SHORT).show();
        });
    }
}
