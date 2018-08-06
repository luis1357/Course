package com.example.admin.saving_data;

import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity:";
    EditText MyEdtTxt;
    TextView MyTxtVw;
    Button btnChangeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyEdtTxt = findViewById(R.id.etName);
        MyTxtVw = findViewById(R.id.tvName);
        btnChangeText = findViewById(R.id.btnChangeText);

        btnChangeText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String data = MyEdtTxt.getText().toString();
                MyTxtVw.setText(data);
            }
        });

        Log.d(TAG, "OnCreate: ");
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

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    public void changeText(View view)
    {
        String data = MyEdtTxt.getText().toString();
        MyTxtVw.setText(data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState: ");

        String data = MyTxtVw.getText().toString();
        outState.putString("data", data);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "onRestoreInstanceState: ");

        MyTxtVw.setText(savedInstanceState.getString("data"));
    }

    public void doSomething(View view)
    {
        switch (view.getId())
        {
            case R.id.btnGoToSecond:
                List<Person> personList = new ArrayList<>();

                personList.add(new Person("sdf", "sdf"));
                personList.add(new Person("abc", "abc"));

                Intent intent = new Intent(this, Main2Activity.class);
                intent.putParcelableArrayListExtra("person",
                                                    (ArrayList<? extends Parcelable>) personList);

                startActivity(intent);
                break;

            case R.id.btnShareData:

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is a message");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
        }
    }
}
