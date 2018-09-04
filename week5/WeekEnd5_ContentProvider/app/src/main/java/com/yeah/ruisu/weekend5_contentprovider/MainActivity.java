package com.yeah.ruisu.weekend5_contentprovider;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yeah.ruisu.weekend5_contentprovider.data.EmployeeContract;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity: ";
    public static final int REQUEST_CODE = 1000;

    TextView tvName, tvAddress, tvAge, tvEmail, tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InstantiateControl();
    }

    public void InstantiateControl()
    {
        tvName = findViewById(R.id.name);
        tvAddress = findViewById(R.id.address);
        tvAge = findViewById(R.id.age);
        tvEmail = findViewById(R.id.email);
        tvPhone = findViewById(R.id.phone);
    }

    private void showMessage(String message, final Boolean successfullySaved)
    {
        try
        {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Message");
            alertDialog.setMessage(message);
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();

                            if (successfullySaved)
                            {
                                Intent intent = new Intent(MainActivity.this,
                                                            DetailActivity.class);
                                startActivityForResult(intent, REQUEST_CODE);
                            }
                        }
                    });

            alertDialog.show();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void saveData(View view)
    {
        try
        {
            if (TextUtils.isEmpty(tvName.getText()))
            {
                showMessage("Name cannot be Empty!", false);
                return;
            }

            ContentValues values = new ContentValues();

            values.put(EmployeeContract.COLUMN_NAME, tvName.getText().toString().trim());
            values.put(EmployeeContract.COLUMN_ADDRESS, tvAddress.getText().toString().trim());
            values.put(EmployeeContract.COLUMN_AGE, tvAge.getText().toString().trim());
            values.put(EmployeeContract.COLUMN_EMAIL, tvEmail.getText().toString().trim());
            values.put(EmployeeContract.COLUMN_PHONE, tvPhone.getText().toString().trim());

            /* ContentResolver will access the Employee Content Provider.*/
            Uri myUri = getContentResolver().insert(EmployeeContract.EMPLOYEE_URI,
                                                    values);

            String myUserId = myUri.getLastPathSegment();

            showMessage("Succesfully saved! New user ID is: " +
                                myUserId,
                        true);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        ResetEditTexts();
    }

    public void ResetEditTexts()
    {
        tvName.setText("");
        tvAge.setText("");
        tvAddress.setText("");
        tvEmail.setText("");
        tvPhone.setText("");
    }
}
