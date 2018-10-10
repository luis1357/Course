package com.example.luisenriquez.udemycourse1_1;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CurrencyActivity extends Activity
{
    EditText etUSD;
    TextView tvMXN;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        bindControls();
    }

    private void bindControls()
    {
        etUSD = findViewById(R.id.editText);
        tvMXN = findViewById(R.id.tvCurrencyinMXN);
    }

    public void convertCurrency(View view)
    {
        double cUSD = Double.parseDouble(etUSD.getText().toString());
        double cMXN = cUSD * 18.50;

        tvMXN.setText(String.valueOf(cMXN));
    }
}
