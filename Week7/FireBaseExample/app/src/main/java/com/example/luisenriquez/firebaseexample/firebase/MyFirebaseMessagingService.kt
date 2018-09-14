package com.example.luisenriquez.firebaseexample.firebase

import android.app.Service
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService()
{
    val TAG = "FirebaseService: "

    override fun onNewToken(p0: String?)
    {
        super.onNewToken(p0)

        Log.d(TAG + " token", p0)
    }

    override fun onMessageReceived(p0: RemoteMessage?)
    {
        super.onMessageReceived(p0)

        Log.d(TAG, p0?.from)

        Log.d(TAG, p0?.notification?.body)
    }
}