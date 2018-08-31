package com.yeah.ruisu.realvolvocodingchallenge.ui

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Binder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.R.attr.layoutManager
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.yeah.ruisu.realvolvocodingchallenge.AppController
import com.yeah.ruisu.realvolvocodingchallenge.R
import com.yeah.ruisu.realvolvocodingchallenge.databinding.ActivityMainBinding
import com.yeah.ruisu.realvolvocodingchallenge.databinding.RecyclerViewItemBinding
import com.yeah.ruisu.realvolvocodingchallenge.di.activity.ActivityModule
import com.yeah.ruisu.realvolvocodingchallenge.utils.Constants
import javax.inject.Inject

class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var mainViewModel: MainViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Glide.with(activityMainBinding.ivBack.context)
                .load(Constants.HOME_IMAGE)
                .into(activityMainBinding.ivBack)


        injectDependencies()
        setUpObservers()
        setUpListeners()

        activityMainBinding.viewModel = mainViewModel
    }

    private fun injectDependencies()
    {
        (application as AppController).getComponent()
                                        .newActivityComponent(ActivityModule(this))
                                        .inject(this)
    }

    private fun setUpObservers()
    {
        mainViewModel.getWeatherForecast().observe(this, Observer {
            activityMainBinding.rvMain.apply{
                adapter = it
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        })

        mainViewModel.singleEventLiveData.observe(this, Observer {
            activityMainBinding.myDrawerLayout.closeDrawers()
        })
    }

    private fun setUpListeners()
    {
        activityMainBinding.navView.setNavigationItemSelectedListener(mainViewModel)
    }
}
