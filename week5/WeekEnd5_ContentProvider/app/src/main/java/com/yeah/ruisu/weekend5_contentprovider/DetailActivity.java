package com.yeah.ruisu.weekend5_contentprovider;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import com.yeah.ruisu.weekend5_contentprovider.data.EmployeeContract;

public class DetailActivity extends Activity
{
    public static final String TAG = "DetailActivity: ";

    TextView tvDetails;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetails = findViewById(R.id.textView);

        setResult(RESULT_OK);

        @SuppressLint("Recycle")
        Cursor cursor = getContentResolver().query(EmployeeContract.EMPLOYEE_URI,
                null, null, null, null);

        assert cursor != null;
        tvDetails.setText("Employee count in Content provider : " +
                            cursor.getCount());
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
