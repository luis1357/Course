package com.example.admin.layout_and_views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    EditText etNumber1, etNumber2;
    TextView tvName;
    Button btnName;

    EditText etPersonName, etPersonGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Bind everything. */
        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        btnName = findViewById(R.id.btnDoMagic);
        tvName = findViewById(R.id.tvName);

        etPersonName = findViewById(R.id.etPersonName);
        etPersonGender = findViewById(R.id.etPersonGender);

        btnName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int etNum1 = Integer.parseInt(etNumber1.getText().toString());
                int etNum2 = Integer.parseInt(etNumber2.getText().toString());

                tvName.setText(String.valueOf(etNum1 + etNum2));
            }
        });

        Log.d(TAG, "OnCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause: ");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: ");
    }

    public void goToSecond(View view) {
        Intent intent = new Intent(this, Main2Activity.class);

        String value = etNumber1.getText().toString();

        intent.setAction("sendingValue");
        intent.putExtra(getString(R.string.KEY_VALUE1), value);

        startActivity(intent);
    }



    public void passPersonToSecond(View view) {

        Intent intent = new Intent(this, Main2Activity.class);
        String name = etPersonName.getText().toString();
        String gender = etPersonGender.getText().toString();

        Person person = new Person(name, gender);
        intent.setAction("sendingPerson");

        intent.putExtra("person", person);
        startActivity(intent);

    }
}
