package com.yeah.ruisu.week3day2;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Recycler_Persons extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__persons);

        DatabaseHelper dbhelper = new DatabaseHelper(this);

        List<Person> personList = dbhelper.getAllData();

        RecyclerView MyRecyclerView = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        PersonRecyclerAdapter MyPersonRcyclrAdptr = new PersonRecyclerAdapter(personList);

        MyRecyclerView.setLayoutManager(layoutManager);
        MyRecyclerView.setAdapter(MyPersonRcyclrAdptr);
        MyRecyclerView.setItemAnimator(itemAnimator);
    }

}
