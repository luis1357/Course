package com.example.admin.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class TestThreadHandlerMessage extends Thread 
{
    Handler MyHandler;
    
    public TestThreadHandlerMessage (Handler InHandler)
    {
        this.MyHandler = InHandler;
    }
    
    @Override
    public void run()
    {
        super.run();
        
        String data = "Message from Test Handler";
        System.out.println("run: Thread " + Thread.currentThread() + 
                                "Data: " + data);
        
        /* Adding the data to be sent to the bundle. */
        Bundle MyBundle = new Bundle();
        MyBundle.putString("KEY_DATA", data);
        
        /* Add the bundle to the message object. */
        Message MyMessage = new Message();
        MyMessage.setData(MyBundle);
        
        /* Send the message object to the handler. */
        MyHandler.sendMessage(MyMessage);
    }
}
