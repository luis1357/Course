package com.yeah.ruisu.weekend2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity
{
    TextView etSMSMsg, etPhnNmbr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSMSMsg = findViewById(R.id.etTextSms);
        etPhnNmbr = findViewById(R.id.etPhoneNmbr);
    }

    public void GoToPdfReader(View view)
    {
        CopyReadAssets();
    }

    private void CopyReadAssets()
    {

        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(getFilesDir(), "geralt.pdf");
        try
        {
            in = assetManager.open("geralt.pdf");
            out = openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("tag", e.getMessage());
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(
                Uri.parse("file://" + getFilesDir() + "/geralt.pdf"),
                "application/pdf");

        startActivity(intent);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

    public void ShowImg(View view)
    {
        FragmentManager fm = getSupportFragmentManager();
        final PuppyFragment PuppFrg = new PuppyFragment();

        PuppFrg.show(fm, "dialog_fragment_img");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                PuppFrg.dismiss();
            }
        }, 3000);

    }

    public void ShowDfltAlrtDlg(View view)
    {
        final AlertDialog.Builder MyBuilder;
        MyBuilder = new AlertDialog.Builder(this);

        MyBuilder.setTitle("Hello World")
                .setMessage("See you!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                    }
                })
                .show();
    }

    public void ShowCstmAlrtDlg(View view)
    {
        LayoutInflater MyInflater = getLayoutInflater();
        View alertLayout = MyInflater.inflate(R.layout.custom_alert, null);

        final EditText etUsername = alertLayout.findViewById(R.id.et_username);
        final EditText etEmail = alertLayout.findViewById(R.id.et_email);
        final CheckBox cbToggle = alertLayout.findViewById(R.id.cb_show_pass);

        cbToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    /* We hide the password. */
                    etEmail.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                    {
                    /* We show the password. */
                    etEmail.setTransformationMethod(null);
                }
            }
        });

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Info");

        /* This is to set our custom layout in the Alert Dialog. */
        alert.setView(alertLayout);
        /* With this, we make sure the dialog doesn't go away if *
         *  the user clicks outside the dialog.                  */
        alert.setCancelable(false);

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String user = etUsername.getText().toString();
                String pass = etEmail.getText().toString();
                Toast.makeText(getBaseContext(), "Username: " + user + " Email: " + pass,
                        Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void ShowLstAlrtDlg(View view)
    {
        /* We need to create the alert dialog builder. */
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        builderSingle.setTitle("Select an Ice Cream: ");

        /* We prepare an array adapter and asign it to the *
         *  precreated alert selector dialog               */
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                                                    android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Vanilla");
        arrayAdapter.add("Strawberry");
        arrayAdapter.add("Chocolate");
        arrayAdapter.add("Napolitan");
        arrayAdapter.add("Rocky Road");
        arrayAdapter.add("Lemon Pie");

        /* We prepare the cancel button to close the dialog. */
        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        /* We prepare the method when the user clicks on an item. */
        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner =
                                        new AlertDialog.Builder(MainActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("You selected: ");

                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                    {
                        dialog.dismiss();
                    }
                });

                builderInner.show();
            }
        });

        builderSingle.show();
    }

    public void CreateNotification(View view)
    {
        /* First we need to check the SDK version of the current  *
         *  smartphone, so we can know which notification builder *
         *  we will need.                                         */
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("default",
                    "MY_CHANNEL",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("MY_CHANNEL_DESCRIPTION");

            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(channel);
        }

        /* Now that we have the channel created, we can create the *
         *  notification builder.                                  */
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "default");

        /* We create the intent that will fire when the user *
         *  clicks on the notification                       */

        Intent intent = new Intent(this, MapsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                                                                            intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.stark);
        mBuilder.setContentTitle("Ready to Explore the World?");
        mBuilder.setContentText("Click on Me!");

        assert mNotificationManager != null;
        mNotificationManager.notify(1, mBuilder.build());
    }

    public void SendSMS(View view)
    {
        String Msg, PhnNmbr;

        Msg = etSMSMsg.getText().toString();
        PhnNmbr = etPhnNmbr.getText().toString();

        try
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(PhnNmbr, null, Msg,
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

    public void GoToCounter(View view)
    {
        startActivity(new Intent(this, Counter.class));
    }
}
