package com.example.luisenriquez.week7day4test

import android.app.Application
import com.example.luisenriquez.week7day4test.di.application.ApplicationComponent
import com.example.luisenriquez.week7day4test.di.application.ApplicationModule
import com.example.luisenriquez.week7day4test.di.application.DaggerApplicationComponent

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