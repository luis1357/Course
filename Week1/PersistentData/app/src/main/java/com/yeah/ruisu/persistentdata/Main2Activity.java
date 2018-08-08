package com.yeah.ruisu.persistentdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class Main2Activity extends Activity
{
    private static final String TAG = "LifeCycle_Second: ";
    TextView MyTextView1, MyTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d(TAG, "onCreate: ");

        MyTextView1 = findViewById(R.id.tvFirstValue);
        MyTextView2 = findViewById(R.id.tvSecondValue);

        SharedPreferences MySharedPreferences = getSharedPreferences(MainActivity.MY_PREF_FILE,
                                                                        Context.MODE_PRIVATE);

        String FrstVal = MySharedPreferences.getString("value1", "default");
        String ScndVal = MySharedPreferences.getString("value2", "default");

        MyTextView1.setText(FrstVal);
        MyTextView2.setText(ScndVal);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
