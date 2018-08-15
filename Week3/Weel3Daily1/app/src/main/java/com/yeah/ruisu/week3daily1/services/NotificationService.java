package com.yeah.ruisu.week3daily1.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.yeah.ruisu.week3daily1.R;

import static com.yeah.ruisu.week3daily1.App.CHANNEL_ID;

public class NotificationService extends Service
{
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        CreateCstmNotification();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    public NotificationService() {
    }

    public void CreateCstmNotification ()
    {
        /* We need to create an Intent for each button. */
        Intent PlayIntent = new Intent("Play_Audio");
        Intent StopIntent = new Intent("Stop_Audio");

        PlayIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        StopIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        /* We create the pending intents. */
        PendingIntent PendingPlayIntent = PendingIntent.getBroadcast(this,
                                                                        0,
                                                                        PlayIntent,
                                                                        0);

        PendingIntent PendingStopIntent = PendingIntent.getBroadcast(this,
                                                                        0,
                                                                        StopIntent,
                                                                        0);

        /* We prepare the custom layout so we can use it. */
        RemoteViews ContentView = new RemoteViews(getPackageName(),
                                                    R.layout.notification_player);

        /* We add the listeners for the clicks of the buttons. */
        ContentView.setOnClickPendingIntent(R.id.btnStop, PendingStopIntent);
        ContentView.setOnClickPendingIntent(R.id.btnPlay, PendingPlayIntent);


        /* Creating the icon for the notification. */
        ContentView.setImageViewResource(R.id.ivStark, R.mipmap.ic_launcher);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,
                                                                                CHANNEL_ID);

        mBuilder.setSmallIcon(R.drawable.stark)
                .setContent(ContentView);

        Notification MyNotification = mBuilder.build();
        MyNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        startForeground(1, MyNotification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
