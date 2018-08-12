package com.yeah.ruisu.weekend2;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

public class Counter extends AppCompatActivity
        implements CounterFragment.OnFragmentInteractionListener
{
    private static final String COUNTER_FRAGMENT_TAG = "CountingFrag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        InitCounterFrag();
    }

    @Override
    public void onFragmentInteraction(int Action)
    {
        if (Action == 1)
        {
            StartCounter();
        }
        else
        {
            StopCounter();
        }
    }

    private void StopCounter()
    {
        RemoveFrags();
        InitCounterFrag();
    }

    private void InitCounterFrag()
    {
        Bundle bundle = new Bundle();
        bundle.putString("Strt", "Zero");

        CountingFrag CounterFrag = new CountingFrag();
        CounterFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flFrag, CounterFrag, COUNTER_FRAGMENT_TAG)
                .addToBackStack(COUNTER_FRAGMENT_TAG)
                .commit();
    }

    private void StartCounter()
    {
        RemoveFrags();

        Bundle bundle = new Bundle();
        bundle.putString("Strt", "Start");

        CountingFrag CounterFrag = new CountingFrag();
        CounterFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.flFrag, CounterFrag, COUNTER_FRAGMENT_TAG)
                .addToBackStack(COUNTER_FRAGMENT_TAG)
                .commit();
    }

    public void RemoveFrags ()
    {
        while(getSupportFragmentManager().getBackStackEntryCount() > 0)
        {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }
}
