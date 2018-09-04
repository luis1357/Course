package com.yeah.ruisu.weekend5_walmartchallenge.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.yeah.ruisu.weekend5_walmartchallenge.AppController;
import com.yeah.ruisu.weekend5_walmartchallenge.R;
import com.yeah.ruisu.weekend5_walmartchallenge.WalmartItemAdapter;
import com.yeah.ruisu.weekend5_walmartchallenge.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity: ";
    private MainViewModel myMainViewModel;
    private ActivityMainBinding myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUpViewModels();
        setUpObservers();

        myBinder.setViewModel(myMainViewModel);
        myBinder.setLifecycleOwner(this);
    }

    private void setUpViewModels()
    {
        myMainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
    }

    private void setUpObservers()
    {
        myMainViewModel.getWeatherForecast("ipod").observe(this,
                itemList ->
                {

                    Log.d(TAG, "setUpObservers: ");
                    myBinder.rvMain.setLayoutManager(new LinearLayoutManager(this));
                    myBinder.rvMain.setAdapter(new WalmartItemAdapter(this,
                            itemList));
                    myBinder.rvMain.setItemAnimator(new DefaultItemAnimator());
                });
    }
}
