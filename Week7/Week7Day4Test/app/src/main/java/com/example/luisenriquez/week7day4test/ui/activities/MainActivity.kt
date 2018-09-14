package com.example.luisenriquez.week7day4test.ui.activities

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.luisenriquez.week7day4test.AppController
import com.example.luisenriquez.week7day4test.R
import com.example.luisenriquez.week7day4test.databinding.ActivityMainBinding
import com.example.luisenriquez.week7day4test.di.activity.ActivityModule
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
        mainViewModel.getRandomNumbers().observe(this, Observer {
            activityMainBinding.rvMain.apply{
                adapter = it
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        })

    }

    private fun setUpListeners()
    {
        //activityMainBinding.navView.setNavigationItemSelectedListener(mainViewModel)
    }

}
