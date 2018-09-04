package com.yeah.ruisu.weekend5_walmartchallenge.di;

import android.content.Context;

import com.yeah.ruisu.weekend5_walmartchallenge.data.remote.RemoteServiceHelper;
import com.yeah.ruisu.weekend5_walmartchallenge.data.repositorymodule.Repository;
import com.yeah.ruisu.weekend5_walmartchallenge.data.repositorymodule.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule
{
    private Context myContext;

    public MainModule(Context myContext)
    {
        this.myContext = myContext;
    }

    @Provides
    @Singleton
    public Context getMyContext()
    {
        return myContext;
    }

    @Provides
    @Singleton
    public RemoteServiceHelper getRemoteServiceHelper()
    {
        return new RemoteServiceHelper();
    }

    @Provides
    @Singleton
    public Repository getRepositoryImpl(RemoteServiceHelper inRmtSrvcHlpr)
    {
        return new RepositoryImpl(inRmtSrvcHlpr);
    }
}
