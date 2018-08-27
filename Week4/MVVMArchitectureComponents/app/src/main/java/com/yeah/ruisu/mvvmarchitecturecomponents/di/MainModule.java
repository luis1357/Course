package com.yeah.ruisu.mvvmarchitecturecomponents.di;

import android.content.Context;

import com.yeah.ruisu.mvvmarchitecturecomponents.data.RepositoryModule.Repository;
import com.yeah.ruisu.mvvmarchitecturecomponents.data.RepositoryModule.RepositoryImpl;
import com.yeah.ruisu.mvvmarchitecturecomponents.data.remote.RemoteServiceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule
{
    Context MMContext;

    public MainModule(Context MMContext)
    {
        this.MMContext = MMContext;
    }

    @Provides
    @Singleton
    public Context getMMContext()
    {
        return MMContext;
    }

    @Provides
    @Singleton
    public RemoteServiceHelper getRemoteServiceHelper()
    {
        return new RemoteServiceHelper();
    }

    @Provides
    @Singleton
    public Repository getRepositoryImpl(RemoteServiceHelper InRemoteServiceHelper)
    {
        return new RepositoryImpl(InRemoteServiceHelper);
    }
}
