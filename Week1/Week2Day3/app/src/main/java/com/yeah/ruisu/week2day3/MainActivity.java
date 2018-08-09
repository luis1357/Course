package com.yeah.ruisu.week2day3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    /* Preparing the Shared Preferences to know if this is the *
     *  first run of the app, so we can know if we need to     *
     *  create the sql database.                               */
    SharedPreferences prefs = null;

    private Context MyContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Getting the shared preferences. */
        prefs = getSharedPreferences("com.yeah.ruisu.week2day3", MODE_PRIVATE);

        MyContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();

        /* We check if we are running the application for the first time. */
        if (prefs.getBoolean("firstrun", true))
        {
            /* After we have done what we need, we just change *
             *  the first run to false.                        */
            FillSql StartTable = new FillSql(MyContext);

            StartTable.CreateAnimals();

            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

    public void GoToCategories(View view)
    {
        startActivity(new Intent(this, Categories.class));



        /*final MediaPlayer mp = MediaPlayer.create(this, R.raw.alligator_snd);
        mp.start();*/
    }
}
