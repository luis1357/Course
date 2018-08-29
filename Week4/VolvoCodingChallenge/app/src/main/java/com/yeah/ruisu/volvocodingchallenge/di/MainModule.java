package com.yeah.ruisu.volvocodingchallenge.di;

import android.content.Context;

import com.yeah.ruisu.volvocodingchallenge.data.RepositoryModule.Repository;
import com.yeah.ruisu.volvocodingchallenge.data.RepositoryModule.RepositoryImpl;
import com.yeah.ruisu.volvocodingchallenge.data.remote.RemoteServiceHelper;

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
