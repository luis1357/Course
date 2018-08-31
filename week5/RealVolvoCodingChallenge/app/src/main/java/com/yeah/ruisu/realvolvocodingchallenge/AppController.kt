package com.yeah.ruisu.realvolvocodingchallenge

import android.app.Application
import com.yeah.ruisu.realvolvocodingchallenge.di.application.ApplicationComponent
import com.yeah.ruisu.realvolvocodingchallenge.di.application.ApplicationModule
import com.yeah.ruisu.realvolvocodingchallenge.di.application.DaggerApplicationComponent

class AppController : Application()
{
    private lateinit var applicationComponent: ApplicationComponent

    fun getComponent() : ApplicationComponent
    {
        return if (this::applicationComponent.isInitialized)
        {
            applicationComponent
        }
        else
        {
            createApplicationComponent()
        }
    }

    private fun createApplicationComponent(): ApplicationComponent
    {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        return applicationComponent
    }
}