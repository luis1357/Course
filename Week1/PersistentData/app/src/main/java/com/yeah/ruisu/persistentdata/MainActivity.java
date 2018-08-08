package com.yeah.ruisu.persistentdata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity 
{

	public static final String MY_PREF_FILE = "mypref_file";
	private static final String TAG = "Lifecycle_Main";
	EditText MyEditText1, MyEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyEditText1 = findViewById(R.id.etVal1);
        MyEditText2 = findViewById(R.id.etVal2);

        Log.d(TAG, "onCreate: ");
    }

    public void SaveData(View view) 
    {
    	SharedPreferences MySharedPreferences = getSharedPreferences(MY_PREF_FILE,
                                                                        Context.MODE_PRIVATE);
        SharedPreferences.Editor MyEditor = MySharedPreferences.edit();

        MyEditor.putString("value1", MyEditText1.getText().toString());
        MyEditor.putString("value2", MyEditText2.getText().toString());
        MyEditor.commit();

        Intent MyIntent = new Intent(this, Main2Activity.class);
        startActivity(MyIntent);
    }

    public void GetData(View view) 
    {
        SharedPreferences MySharedPreferences = getSharedPreferences(MY_PREF_FILE,
                                                                        Context.MODE_PRIVATE);

        Log.d(TAG, "GetData: " + MySharedPreferences.getString("value1",
                                                                        "default"));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        Log.d(TAG, "onConfigurationChanged: ");

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            Toast.makeText(this, "Landscape", Toast.LENGTH_SHORT).show();
        }

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Toast.makeText(this, "Portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart()
    {
    	super.onStart();
    	Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
