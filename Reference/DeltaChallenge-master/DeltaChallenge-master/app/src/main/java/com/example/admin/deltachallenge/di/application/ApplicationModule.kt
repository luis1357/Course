package com.example.admin.deltachallenge.di.application

import android.content.Context
import com.example.admin.deltachallenge.AppController
import com.example.admin.deltachallenge.data.remote.RemoteServiceHelper
import com.example.admin.deltachallenge.data.repository.Repository
import com.example.admin.deltachallenge.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: AppController) {

    @Provides
    @ApplicationScope
    fun providesApplicationContext(): Context {
        return application
    }

    @Provides
    @ApplicationScope
    fun providesRemoteServiceHelper(): RemoteServiceHelper {
        return RemoteServiceHelper()
    }

    @Provides
    @ApplicationScope
    fun providesRepository(remoteServiceHelper: RemoteServiceHelper): Repository {
        return RepositoryImpl(remoteServiceHelper)
    }
}