package com.example.admin.layout_and_views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private static String TAG = "Main2Activity";

    /* we declare the controls. */
    TextView tvName, tvNameO, tvGender, tvGenderO, tvValue, tvValueO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        tvName = findViewById(R.id.textView);
        tvNameO = findViewById(R.id.textView2);

        tvGender = findViewById(R.id.textView3);
        tvGenderO = findViewById(R.id.textView4);

        tvValue = findViewById(R.id.textView5);
        tvValueO = findViewById(R.id.textView6);

        Intent intent = getIntent();

        switch (intent.getAction())
        {
            case "sendingValue":

                tvName.setVisibility(View.GONE);
                tvNameO.setVisibility(View.GONE);
                tvGender.setVisibility(View.GONE);
                tvGenderO.setVisibility(View.GONE);

                tvValue.setVisibility(View.VISIBLE);
                tvValueO.setVisibility(View.VISIBLE);

                tvValueO.setText(getString(R.string.KEY_VALUE1));

                Log.d(TAG, "onCreate: " +
                        intent.getStringExtra(getString(R.string.KEY_VALUE1)));
                break;

            case "sendingPerson":
                Person person = (Person) intent.getSerializableExtra("person");

                tvName.setVisibility(View.VISIBLE);
                tvNameO.setVisibility(View.VISIBLE);
                tvGender.setVisibility(View.VISIBLE);
                tvGenderO.setVisibility(View.VISIBLE);

                tvValue.setVisibility(View.GONE);
                tvValueO.setVisibility(View.GONE);

                tvNameO.setText(person.getName());
                tvGenderO.setText(person.getGender());

                Log.d(TAG, "onCreate: " + person.getName() + " " + person.getGender());
                break;
        }
    }
}
