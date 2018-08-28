package com.yeah.ruisu.week5day1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10;
    public static final String TAG = "MainActivity: ";

    private EditText etContact;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContact = findViewById(R.id.etContactName);

        checkPermissions();
    }

    private void checkPermissions()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            /* Should we show explanation. */
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_CONTACTS))
            {
                /* Show an explanation to the user *asynchronously* -- don't *
                 *  block this thread waiting for the user's response! After *
                 *  the user sees the explanation, try again to request the  *
                 *  permission.                                              */

                ShowMyAlertDialog();

                Log.d(TAG, "checkPermissions: Please allow this permission!");
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
            else
            {
                /* No explanation needed, we can request the permission. */
                Log.d(TAG, "checkPermissions: Requesting Permission...");

                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                /* My_PERMISSIONS_REQUEST_READ_CONTACTS is an app-defined *
                 *  int constant. The callback method gets the result of  *
                 *  the request.                                          */
            }
        }
        else
        {
            Log.d(TAG, "checkPermissions: Permission is already granted!");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                /* If request is cancelled, the result array is empty. */
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.d(TAG, "onRequestPermissionsResult: Granted!");
                }
                else
                {
                    Log.d(TAG, "onRequestPermissionsResult: Denied!");
                }
                break;
        }
    }

    public void getPhoneNumber(String name, Context context)
    {
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Uri phoneURI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //String selection = DISPLAY_NAME + "=?";
        String selection = DISPLAY_NAME +" like'%" + name +"%'";

        String[] selectionArgs = new String[] {name};

        String[] projection = new String[] { NUMBER };

        /*Cursor c = context.getContentResolver()
                            .query(phoneURI,
                                    projection,
                                    selection,
                                    selectionArgs,
                                    null);*/

        Cursor c = context.getContentResolver()
                .query(phoneURI,
                        projection,
                        selection,
                        null,
                        null);

        assert c != null;
        while (c.moveToNext())
        {
            Log.d(TAG, "SearchContact: " +
                    c.getString(c
                            .getColumnIndex(NUMBER)));

            Toast.makeText(this, c.getString(c
                    .getColumnIndex(NUMBER)), Toast.LENGTH_SHORT).show();
        }

        c.close();
    }

    public void readPhoneContacts(View view)
    {
        getPhoneNumber(etContact.getText().toString(), this);

    }

    public void ShowMyAlertDialog ()
    {
        AlertDialog.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            builder = new AlertDialog.Builder(this,
                                                android.R.style.Theme_Material_Dialog_Alert);
        }
        else
        {
            builder = new AlertDialog.Builder(this);
        }

        builder.setTitle("Permission Needed!")
                .setMessage("This application needs to read contact info so I can get a Job!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this,
                                        "Thanks =D",
                                            Toast.LENGTH_SHORT).show();

                        checkPermissions();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(MainActivity.this,
                                "You will regret this!",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
