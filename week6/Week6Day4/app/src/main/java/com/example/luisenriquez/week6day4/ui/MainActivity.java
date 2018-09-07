package com.example.luisenriquez.week6day4.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.luisenriquez.week6day4.R;
import com.example.luisenriquez.week6day4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;
    private ActivityMainBinding myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUpViewModels();
        setUpObservers();

        myBinder.setViewModel(mainViewModel);
        myBinder.setLifecycleOwner(this);
    }

    private void setUpViewModels()
    {
        mainViewModel = ViewModelProviders.of(this)
                                            .get(MainViewModel.class);
    }

    private void setUpObservers()
    {
        mainViewModel.getMovieResponse("marvel").observe(this,
                movieList ->
                {
                    Log.d(TAG, "setUpObservers: ");
                    myBinder.rvMain.setLayoutManager(new LinearLayoutManager(this));
                    myBinder.rvMain.setAdapter(new MovieRecyclerAdapter(this,
                            movieList));
                    myBinder.rvMain.setItemAnimator(new DefaultItemAnimator());
                });
    }
}
