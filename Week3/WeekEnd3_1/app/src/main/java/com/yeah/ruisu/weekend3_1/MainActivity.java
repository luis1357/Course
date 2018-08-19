package com.yeah.ruisu.weekend3_1;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG ="MainActivity";

    TextView etSMSMsg, etPhnNmbr, tvCatchedMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSMSMsg = findViewById(R.id.etTextSms);
        etPhnNmbr = findViewById(R.id.etPhoneNmbr);
        tvCatchedMsg = findViewById(R.id.tvCatchedMessage);
    }

    public void btnSendSMS(View view)
    {
        String Msg, PhnNmbr;

        Msg = etSMSMsg.getText().toString();
        PhnNmbr = etPhnNmbr.getText().toString();

        SendSMS(Msg, PhnNmbr);

        try
        {
            Thread.sleep(2000);
            Toast.makeText(this, getLastSms(), Toast.LENGTH_SHORT).show();

            tvCatchedMsg.setText(getLastSms());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    public void SendSMS(String Msg, String PhnNumber)
    {
        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(PhnNumber, null, Msg,
                    null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public String getLastSms()
    {


        @SuppressLint("Recycle")
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/sent"),
                                                                null,
                                                                null,
                                                                null,
                                                                null);

        assert cursor != null;
        cursor.moveToNext();

        return cursor.getString(cursor.getColumnIndex("body"));

    }
}
