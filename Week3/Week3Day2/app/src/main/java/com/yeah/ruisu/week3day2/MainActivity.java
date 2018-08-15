package com.yeah.ruisu.week3day2;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
{
    /* Defining the controls. */
    EditText etName, etAge, etNationality;
    Spinner spGender;

    private MyReceiver myReceiver;
    private MyScndReceiver my2Receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyReceiver();
        my2Receiver = new MyScndReceiver();

        BindControls();

        InstantiateMySpinner();
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.MY_BROADCAST);
        registerReceiver(myReceiver, intentFilter);

        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(Constants.MY_SERVICE_BROADCAST);
        registerReceiver(my2Receiver, intentFilter2);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        unregisterReceiver(myReceiver);
        unregisterReceiver(my2Receiver);
    }

    public void BindControls()
    {
        etName = findViewById(R.id.etPersonName);
        etAge = findViewById(R.id.etAge);
        etNationality = findViewById(R.id.etNationality);

        spGender = findViewById(R.id.spGender);
    }

    public void InstantiateMySpinner()
    {
        /* Create an ArrayAdapter using the string array and *
         *  a default spinner layout                         */
        ArrayAdapter<CharSequence> adapter =
                                    ArrayAdapter.createFromResource(this,
                                                            R.array.gender_array,
                                                            android.R.layout.simple_spinner_item);
        /* Specify the layout to use when the list of choices appears. */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /* Apply the adapter to the spinner. */
        spGender.setAdapter(adapter);
    }

    public void SubmitPerson(View view)
    {
        /* We prepare the strings to receive from the View. */
        String PName, PAge, PGender, PNation;

        PName = etName.getText().toString();
        PAge = etAge.getText().toString();
        PGender = spGender.getSelectedItem().toString();
        PNation = etNationality.getText().toString();

        Intent intent = new Intent();

        intent.setAction(Constants.MY_BROADCAST);

        intent.putExtra("PName", PName);
        intent.putExtra("PAge", PAge);
        intent.putExtra("PGender", PGender);
        intent.putExtra("PNation", PNation);

        sendBroadcast(intent);
    }

    public void GoToList(View view)
    {
        startActivity(new Intent(this, Recycler_Persons.class));
    }
}
