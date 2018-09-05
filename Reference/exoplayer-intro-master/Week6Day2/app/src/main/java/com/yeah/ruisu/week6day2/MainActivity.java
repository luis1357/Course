package com.yeah.ruisu.week6day2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    MyCustomView myCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCustomView = (MyCustomView) findViewById(R.id.myCstmVw);
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnOne:
            {
                myCustomView.customPaddingUp(30);
                break;
            }
            case R.id.btnTwo:
            {
                myCustomView.swapColor();
                break;
            }
            case R.id.btnThree:
            {
                myCustomView.customPaddingDown(30);
                break;
            }
        }
    }
}
