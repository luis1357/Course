package com.yeah.ruisu.realvolvocodingchallenge.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.yeah.ruisu.realvolvocodingchallenge.AppController
import com.yeah.ruisu.realvolvocodingchallenge.R
import com.yeah.ruisu.realvolvocodingchallenge.adapter.WeatherAdapter
import com.yeah.ruisu.realvolvocodingchallenge.data.RepositoryModule.Repository
import com.yeah.ruisu.realvolvocodingchallenge.utils.SingleEventLiveData
import javax.inject.Inject

class MainViewModel (val context: Application) : AndroidViewModel(context),
        NavigationView.OnNavigationItemSelectedListener
{
    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var singleEventLiveData: SingleEventLiveData<Boolean>

    /* init is the constructor of a class in kotlin. */
    init
    {
        (context as AppController).getComponent().inject(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    {
        when (item.title)
        {
            context.getString(R.string.gothenburg) -> makeWeatherForecastCall("890869")
            context.getString(R.string.stockholm) -> makeWeatherForecastCall("906057")
            context.getString(R.string.mountain_view) -> makeWeatherForecastCall("2455920")
            context.getString(R.string.london) -> makeWeatherForecastCall("44418")
            context.getString(R.string.new_york) -> makeWeatherForecastCall("2459115")
            context.getString(R.string.Berlin) -> makeWeatherForecastCall("638242")
        }

        item.isChecked = true
        singleEventLiveData.value = true

        return true
    }

    fun getWeatherForecast() : LiveData<WeatherAdapter>
    {
        return Transformations.map(repository.weatherForecastLiveData)
        {
            list ->
                WeatherAdapter(list, context)
        }
    }

    private fun makeWeatherForecastCall(WOEID: String)
    {
        repository.getWeatherForecast(WOEID)
    }

}