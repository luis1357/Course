package com.yeah.ruisu.realvolvocodingchallenge.di.application

import android.content.Context
import com.yeah.ruisu.realvolvocodingchallenge.AppController
import com.yeah.ruisu.realvolvocodingchallenge.data.RepositoryModule.Repository
import com.yeah.ruisu.realvolvocodingchallenge.data.RepositoryModule.RepositoryImpl
import com.yeah.ruisu.realvolvocodingchallenge.data.remote.RemoteServiceHelper
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