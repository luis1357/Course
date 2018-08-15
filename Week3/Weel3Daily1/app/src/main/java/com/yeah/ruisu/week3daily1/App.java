package com.yeah.ruisu.week3daily1;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application
{
    public static final String CHANNEL_ID = "MyChannel";

    @Override
    public void onCreate()
    {
        super.onCreate();

        CreateNotificationChannel();
    }

    private void CreateNotificationChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel MyServiceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "My Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager MyManager = getSystemService(NotificationManager.class);

            assert MyManager != null;
            MyManager.createNotificationChannel(MyServiceChannel);
        }
    }
}
