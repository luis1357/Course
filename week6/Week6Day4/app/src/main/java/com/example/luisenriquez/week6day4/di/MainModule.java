package com.example.luisenriquez.week6day4.di;

import android.content.Context;


import com.example.luisenriquez.week6day4.data.remote.RemoteServiceHelper;
import com.example.luisenriquez.week6day4.data.repositorymodule.Repository;
import com.example.luisenriquez.week6day4.data.repositorymodule.RepositoryImpl;

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
