package com.example.admin.saving_data;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        List<Person> personList = getIntent().getParcelableArrayListExtra("person");
        Toast.makeText(this, personList.size() + "", Toast.LENGTH_SHORT).show();
    }

}
