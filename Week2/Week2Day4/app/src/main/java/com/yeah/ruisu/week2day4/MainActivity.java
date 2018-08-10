package com.yeah.ruisu.week2day4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements ListFragment.OnFragmentInteractionListener
{
    private ListView lvCelebrity;
    private List<String> ClbrtyNms;

    private static final String CELEBRITY_FRAGMENT_TAG = "CelebrityFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindControls();
        InitData();

        ArrayAdapter<String> MyArrayAdapter = new ArrayAdapter<>(this,
                                                            android.R.layout.simple_list_item_1,
                                                            ClbrtyNms);

        lvCelebrity.setAdapter(MyArrayAdapter);

        //AddFragment("triss");


    }

    public void BindControls ()
    {
        lvCelebrity = findViewById(R.id.lvClbrtyLst);
    }

    public void InitData ()
    {
        ClbrtyNms = new ArrayList<>();

        ClbrtyNms.add("Anna Henrietta");
        ClbrtyNms.add("Calanthe Fiona Riannon");
        ClbrtyNms.add("Cerys an Craite");
        ClbrtyNms.add("Cirilla Fiona Elen Riannon");
        ClbrtyNms.add("Crevan Espane aep Caomhan Macha");
        ClbrtyNms.add("Emhyr var Emreis");
        ClbrtyNms.add("Emiel Regis Rohellec Terzieff-Godefroy");
        ClbrtyNms.add("Eredin Bréacc Glas");
        ClbrtyNms.add("Geralt of Rivia");
        ClbrtyNms.add("Julian Alfred Pankratz");
        ClbrtyNms.add("Triss Merigold");
    }

    public void AddFragment (String Celeb)
    {
        CelebrityFragment CelFrag = CelebrityFragment.newInstance(Celeb);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flFrag, CelFrag, CELEBRITY_FRAGMENT_TAG)
                .addToBackStack(CELEBRITY_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void onFragmentInteraction(int CelebId)
    {
        RemoveFrags();

        String Celeb = BindCelebId(CelebId);

        AddFragment(Celeb);
    }

    public String BindCelebId (int CelebId)
    {
        switch (CelebId)
        {
            case 0:
                return "anna";
            case 1:
                return "calanthe";
            case 2:
                return "cerys";
            case 3:
                return "ciri";
            case 4:
                return "aval";
            case 5:
                return "emhy";
            case 6:
                return "regis";
            case 7:
                return "eredin";
            case 8:
                return "geralt";
            case 9:
                return "jaskier";
            case 10:
                return "triss";
        }

        return "";
    }

    public void RemoveFrags ()
    {
        while(getSupportFragmentManager().getBackStackEntryCount() > 0)
        {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }
}
