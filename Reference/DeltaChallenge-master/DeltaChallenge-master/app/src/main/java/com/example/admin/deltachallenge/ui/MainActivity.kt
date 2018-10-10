package com.example.admin.deltachallenge.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.deltachallenge.AppController
import com.example.admin.deltachallenge.databinding.ActivityMainBinding
import com.example.admin.deltachallenge.di.activity.ActivityComponent
import com.example.admin.deltachallenge.di.activity.ActivityModule
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var binding: ActivityMainBinding
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    private fun injectDependencies() {
        activityComponent = (application as AppController).getComponent().newActivityComponent(ActivityModule(this))
        activityComponent.inject(this)
    }
}
