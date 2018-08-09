package com.yeah.ruisu.week2day3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Categories extends AppCompatActivity
{
    private ListView lvCategories;
    private List<String> lstCtgrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        BindControls();
        InitData();

        ArrayAdapter<String> CtgrsArryAdptr = new ArrayAdapter<String>(this,
                                                                android.R.layout.simple_list_item_1,
                                                                lstCtgrs);

        lvCategories.setAdapter(CtgrsArryAdptr);
    }

    public void BindControls ()
    {
        lvCategories = findViewById(R.id.lvCategories);
    }

    public void InitData()
    {
        lstCtgrs = new ArrayList<>();
        lstCtgrs.add("Amphibians");
        lstCtgrs.add("Birds");
        lstCtgrs.add("Invertebrates");
        lstCtgrs.add("Mammals");
        lstCtgrs.add("Reptiles");
    }

    public void GoToAnimals(View view) {
        startActivity(new Intent(this, RecyclerAnimal.class));
    }
}
