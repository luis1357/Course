package com.yeah.ruisu.restcalls;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class NativeReceiver extends BroadcastReceiver
{
    public static final String TAG = "NativeReceiver: ";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.d(TAG, "onReceive: " +
                    intent.getIntExtra(Constants.Key.CODE, 0) +
                    "\n" +
                    intent.getStringExtra(Constants.Key.MESSAGE));

        String MyResponse = intent.getStringExtra(Constants.Key.RESPONSE);

        try
        {
            JSONObject MyReader = new JSONObject(MyResponse);
            JSONObject JWidget = MyReader.getJSONObject("widget");
            JSONObject JText = JWidget.getJSONObject("text");

            String JTStyle = JText.getString("style");

            Toast.makeText(context, JTStyle, Toast.LENGTH_SHORT).show();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
