package com.yeah.ruisu.week3test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    EditText etInSort, etSubstrings;
    TextView tvOutSort, tvSubstrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInSort = findViewById(R.id.etNumberInput);
        tvOutSort = findViewById(R.id.tvSortedArray);

        etSubstrings = findViewById(R.id.etInSubstring);
        tvSubstrings = findViewById(R.id.tvSubstringsOut);

    }

    public void MySort(View view)
    {
        String str = etInSort.getText().toString();
        int length = str.length();

        int[] arr = new int[length];

        for (int i = 0; i < length; i++)
        {
            arr[i] = Character.getNumericValue(str.charAt(i));
        }

        Arrays.sort(arr);

        tvOutSort.setText(Arrays.toString(arr));
    }

    public void GetSubstrings(View view)
    {
        String input = etSubstrings.getText().toString();

        ArrayList<String> arr = new ArrayList<>();

        StringBuilder str= new StringBuilder();
        String substr = "";

        for (int i = 0; i < input.length(); i++)
        {
            for (int j = 0; i+j <= input.length(); j++)
            {
                substr = input.substring(j, i + j);
                if("".equals(substr)) {
                    continue;
                }

                str.append(substr);
                str.append("\n");
            }
        }
        //return str + input;

        tvSubstrings.setText(str + input);
    }
}
