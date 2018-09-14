package com.example.luisenriquez.week7day4test.di.application

import android.content.Context
import com.example.luisenriquez.week7day4test.AppController
import com.example.luisenriquez.week7day4test.data.remote.RemoteServiceHelper
import com.example.luisenriquez.week7day4test.data.repositorymodule.Repository
import com.example.luisenriquez.week7day4test.data.repositorymodule.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application : AppController)
{
    @Provides
    @ApplicationScope
    fun providesApplicationContext () : Context
    {
        return application
    }

    @Provides
    @ApplicationScope
    fun getRemoteServiceHelper() : RemoteServiceHelper
    {
        return RemoteServiceHelper()
    }

    @Provides
    @ApplicationScope
    fun getRepositoryImpl(remoteServiceHelper: RemoteServiceHelper) : Repository
    {
        return RepositoryImpl(remoteServiceHelper)
    }
}