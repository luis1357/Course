package com.example.luisenriquez.udemycourse1_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "Main Activity: ";

    EditText etName;
    ImageView ivSwitcher;

    boolean switcher = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindControls();
    }

    public void BtnClick(View view)
    {
        String tMsg = "Hello there " +
                        etName.getText().toString() +
                        "!";

        Toast.makeText(this,
                        tMsg,
                        Toast.LENGTH_SHORT)
                .show();

        Log.i(TAG, "Button Clicked!");
    }

    private void bindControls()
    {
        etName = findViewById(R.id.etName);
        ivSwitcher = findViewById(R.id.ivSwitcher);
    }

    public void sWitchImage(View view)
    {
        if (switcher)
        {
            ivSwitcher.setImageResource(R.drawable.husky1);
            switcher = false;
        }
        else
        {
            ivSwitcher.setImageResource(R.drawable.husky2);
            switcher = true;
        }
    }

    public void goToCurrencyActivity(View view)
    {
        startActivity(new Intent(this, CurrencyActivity.class));
    }
}
