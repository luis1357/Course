package com.example.admin.multithreading;

import android.os.Handler;
import android.os.Message;

public class CallbackHandler implements Handler.Callback
{
    @Override
    public boolean handleMessage (Message InMessage)
    {
        System.out.println("CallbackHandler" +
                            InMessage.getData().getString("KEY_DATA"));

        return false;
    }
}
