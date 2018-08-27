package com.yeah.ruisu.mvvmarchitecturecomponents.ui;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStores;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.yeah.ruisu.mvvmarchitecturecomponents.AppController;
import com.yeah.ruisu.mvvmarchitecturecomponents.ForecastRecyclerAdapter;
import com.yeah.ruisu.mvvmarchitecturecomponents.R;
import com.yeah.ruisu.mvvmarchitecturecomponents.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{

    public static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;
    private ActivityMainBinding MyBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUpViewModels();
        setUpObservers();

        MyBinder.setViewModel(mainViewModel);
        MyBinder.setLifecycleOwner(this);

    }

    private void setUpViewModels()
    {
        mainViewModel = ViewModelProviders.of(this)
                                            .get(MainViewModel.class);
    }

    private void InjectDependencies()
    {
        ((AppController) getApplication()).getAppComponent().inject(this);
    }

    private void setUpObservers()
    {
        mainViewModel.getWeatherForecast().observe(this,
                WeatherList ->
                {
                    Log.d(TAG, "setUpObservers: ");
                    MyBinder.rvMain.setLayoutManager(new LinearLayoutManager(this));
                    MyBinder.rvMain.setAdapter(new ForecastRecyclerAdapter(this,
                                                                            WeatherList));
                    MyBinder.rvMain.setItemAnimator(new DefaultItemAnimator());
                });
    }
}
