package com.yeah.ruisu.volvocodingchallenge.ui;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yeah.ruisu.volvocodingchallenge.R;
import com.yeah.ruisu.volvocodingchallenge.databinding.ActivityMainBinding;
import com.yeah.ruisu.volvocodingchallenge.utils.Constants;

import java.sql.Struct;
import java.util.Calendar;
import java.util.Date;

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

    @SuppressLint("SetTextI18n")
    private void setUpObservers()
    {

        GothenBergWeather();
        StockholmWeather();
        MountainVwWeather();
        LondonWeather();
        NewYorkWeather();
        BerlinWeather();
    }

    public void GothenBergWeather ()
    {
        mainViewModel.getGothWeatherForecast().observe(this,
                WeatherList ->
                {
                    Log.d(TAG, "setUpObservers: ");

                    Glide.with(this)
                            .load(Constants.URLS.WEATHER_API_BASE_URL +
                                    Constants.URLS.WEATHER_ICON_URL +
                                    WeatherList.get(0).getWeatherStateAbbr() +
                                    ".png")
                            .into(myBinder.ivForeCstmIcon);


                });
    }

    public void StockholmWeather ()
    {
        mainViewModel.getStockWeatherForecast().observe(this,
                WeatherList ->
                {
                    Log.d(TAG, "setUpObservers: ");

                    Glide.with(this)
                            .load(Constants.URLS.WEATHER_API_BASE_URL +
                                    Constants.URLS.WEATHER_ICON_URL +
                                    WeatherList.get(0).getWeatherStateAbbr() +
                                    ".png")
                            .into(myBinder.ivForeStockIcon);
                });
    }

    public void MountainVwWeather ()
    {
        mainViewModel.getMntnVwkWeatherForecast().observe(this,
                WeatherList ->
                {
                    Log.d(TAG, "setUpObservers: ");

                    Glide.with(this)
                            .load(Constants.URLS.WEATHER_API_BASE_URL +
                                    Constants.URLS.WEATHER_ICON_URL +
                                    WeatherList.get(0).getWeatherStateAbbr() +
                                    ".png")
                            .into(myBinder.ivForeMntnVwIcon);
                });
    }

    public void LondonWeather ()
    {
        mainViewModel.getLondonWeatherForecast().observe(this,
                WeatherList ->
                {
                    Log.d(TAG, "setUpObservers: ");

                    Glide.with(this)
                            .load(Constants.URLS.WEATHER_API_BASE_URL +
                                    Constants.URLS.WEATHER_ICON_URL +
                                    WeatherList.get(0).getWeatherStateAbbr() +
                                    ".png")
                            .into(myBinder.ivForeLndnVwIcon);
                });
    }

    public void NewYorkWeather ()
    {
        mainViewModel.getNwYrkWeatherForecast().observe(this,
                WeatherList ->
                {
                    Log.d(TAG, "setUpObservers: ");

                    Glide.with(this)
                            .load(Constants.URLS.WEATHER_API_BASE_URL +
                                    Constants.URLS.WEATHER_ICON_URL +
                                    WeatherList.get(0).getWeatherStateAbbr() +
                                    ".png")
                            .into(myBinder.ivForeNwYrkVwIcon);
                });
    }

    public void BerlinWeather ()
    {
        mainViewModel.getBrlnWeatherForecast().observe(this,
                WeatherList ->
                {
                    Log.d(TAG, "setUpObservers: ");

                    Glide.with(this)
                            .load(Constants.URLS.WEATHER_API_BASE_URL +
                                    Constants.URLS.WEATHER_ICON_URL +
                                    WeatherList.get(0).getWeatherStateAbbr() +
                                    ".png")
                            .into(myBinder.ivForeBrlnIcon);
                });
    }

    public void GetGW(View view)
    {
        GothenBergWeather();
    }

    public void GetS(View view)
    {
        StockholmWeather();
    }
}
