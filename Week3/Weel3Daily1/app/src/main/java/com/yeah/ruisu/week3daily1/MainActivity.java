package com.yeah.ruisu.week3daily1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.yeah.ruisu.week3daily1.services.NotificationService;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void StartService(View view)
    {
        Intent ServIntent = new Intent(this, NotificationService.class);

        ContextCompat.startForegroundService(this, ServIntent);
    }

    public void StopService(View view)
    {
        Intent serviceIntent = new Intent(this, NotificationService.class);

        stopService(serviceIntent);
    }

    public static class MyMediaReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            if(action != null)
            {
                switch (action)
                {
                    case "Play_Audio":
                        MusicControl.getInstance(context).playMusic();

                        break;

                    case "Stop_Audio":
                        MusicControl.getInstance(context).stopMusic();
                        break;
                }
            }


        }
    }
}
