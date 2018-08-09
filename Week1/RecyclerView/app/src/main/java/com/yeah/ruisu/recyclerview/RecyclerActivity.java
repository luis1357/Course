package com.yeah.ruisu.recyclerview;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends Activity {

    private List<Celebrity> celebrityList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        celebrityList = new ArrayList<>();
        celebrityList.add(new Celebrity("Drake", 32, 200));
        celebrityList.add(new Celebrity("Jennifer Aniston", 50, 130));
        celebrityList.add(new Celebrity("Tom Cruise", 54, 170));

        RecyclerView MyRecyclerView = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        CelebrityRecyclerAdapter MyClbrtyRcyclrAdptr = new CelebrityRecyclerAdapter(celebrityList);

        MyRecyclerView.setLayoutManager(layoutManager);
        MyRecyclerView.setAdapter(MyClbrtyRcyclrAdptr);
        MyRecyclerView.setItemAnimator(itemAnimator);
    }

}
