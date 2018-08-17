package com.yeah.ruisu.restcalls;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MyIntentService extends IntentService
{
    public static final String TAG = "MyIntentService: ";

    public MyIntentService() {
        super("Intent");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        Log.d(TAG, "onHandleIntent: ");

        assert intent != null;
        String url = intent.getStringExtra(Constants.Key.URL);
        Intent newIntent = new Intent();

        Scanner MyScanner = null;

        try
        {
            URL MyConnectionUrl = new URL(url);
            HttpURLConnection MyHttpURLCnnctn =
                    (HttpURLConnection) MyConnectionUrl.openConnection();

            MyHttpURLCnnctn.connect();
            InputStream MyInputStrm = new BufferedInputStream(MyHttpURLCnnctn.getInputStream());

            MyScanner = new Scanner(MyInputStrm);

            StringBuilder sb = new StringBuilder();

            while(MyScanner.hasNext())
            {
                sb.append(MyScanner.nextLine());
            }

            int StatusCode = MyHttpURLCnnctn.getResponseCode();
            String StatusMsg = MyHttpURLCnnctn.getResponseMessage();

            newIntent.setAction(Constants.NATIVE_RECEIVER_ACTION);

            newIntent.putExtra(Constants.Key.CODE, StatusCode);
            newIntent.putExtra(Constants.Key.MESSAGE, StatusMsg);
            newIntent.putExtra(Constants.Key.RESPONSE, sb.toString());

            sendBroadcast(newIntent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert MyScanner != null;
            MyScanner.close();
        }
    }
}
