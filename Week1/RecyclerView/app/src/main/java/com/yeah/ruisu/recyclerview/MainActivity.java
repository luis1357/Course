package com.yeah.ruisu.recyclerview;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ListView lvMain;
    private List<Celebrity> celebrityList;
    private ListView lvCelebrity;
    private List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindControls();
        InitData();

        ArrayAdapter<String> MyArrayAdapter = new ArrayAdapter<String>(this,
                                                            android.R.layout.simple_list_item_1,
                                                            stringList);

        lvMain.setAdapter(MyArrayAdapter);

        CelebrityListAdapter MyClbrtyLstAdptr = new CelebrityListAdapter(this,
                                                                    R.layout.celebrity_list_item,
                                                                    celebrityList);

        lvCelebrity.setAdapter(MyClbrtyLstAdptr);
    }

    public void BindControls()
    {
        lvMain = findViewById(R.id.lvMain);
        lvCelebrity = findViewById(R.id.lvCelebrity);
    }

    public void InitData()
    {
        stringList = new ArrayList<>();
        stringList.add("First");
        stringList.add("Second");
        stringList.add("Third");
        stringList.add("Fourth");

        celebrityList = new ArrayList<>();
        celebrityList.add(new Celebrity("Drake", 32, 200));
        celebrityList.add(new Celebrity("Jennifer Aniston", 50, 130));
        celebrityList.add(new Celebrity("Tom Cruise", 54, 170));
    }

    public void GoToRecycler(View view)
    {
        startActivity(new Intent(this, RecyclerActivity.class));
    }
}
