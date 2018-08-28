package com.yeah.ruisu.retrievephonecontacts;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

    public void readPhoneContacts(View view)
    {
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        @SuppressLint("Recycle")
        Cursor myCursor = getContentResolver().query(contentUri,
                                                        null,
                                                        null,
                                                        null,
                                                        null);

        int hasPhone = 0;

        assert myCursor != null;
        if (myCursor.getCount() > 0)
        {
            while (myCursor.moveToNext())
            {
                String CONTACT_NAME = etContact.getText().toString();
                /*String CONTACT_NAME = myCursor.getString(myCursor
                                                            .getColumnIndex(DISPLAY_NAME));*/

                Log.d(TAG, "readPhoneContacts: " + CONTACT_NAME);

                myCursor.moveToNext();
                hasPhone = Integer.parseInt(
                        myCursor.getString(
                                myCursor.getColumnIndex(HAS_PHONE_NUMBER)
                        )
                );

                if (hasPhone > 0)
                {
                    Uri phoneURI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                    String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

                    String[] projection = new String[] {NUMBER};
                    String selection = DISPLAY_NAME + "=?";
                    String[] selectionArgs = new String[] {CONTACT_NAME};

                    Cursor phoneCursor = getContentResolver().query(phoneURI,
                                                                        projection,
                                                                        selection,
                                                                        selectionArgs,
                                                                        null);

                    while (phoneCursor.moveToNext())
                    {
                        Log.d(TAG, "SearchContact: " +
                                        phoneCursor.getString(phoneCursor
                                                    .getColumnIndex(NUMBER)));
                    }
                }
            }
        }
    }
}
