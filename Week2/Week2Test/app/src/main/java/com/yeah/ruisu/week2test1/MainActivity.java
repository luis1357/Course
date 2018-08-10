package com.yeah.ruisu.week2test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AddCarFragment.OnFragmentInteractionListener
{

    private static final String LIST_FRAGMENT_TAG = "ListFragment";

    private List<Car> CarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    @Override
    public void onFragmentInteraction(String CarNm, String CarType,
                                      String CarYear)
    {
        Toast.makeText(this, CarNm, Toast.LENGTH_SHORT).show();
        AddFragment(CarNm, CarType, CarYear);

    }


    public void AddFragment (String CarNm, String CarType,
                             String CarYear)
    {

        ListFragment ListFrag = ListFragment.newInstance(CarNm, CarType, CarYear);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flFrag, ListFrag, LIST_FRAGMENT_TAG)
                .addToBackStack(LIST_FRAGMENT_TAG)
                .commit();
    }

}
