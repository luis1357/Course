package com.yeah.ruisu.mvp_dagger.view.mainactivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yeah.ruisu.mvp_dagger.MyApp;
import com.yeah.ruisu.mvp_dagger.R;
import com.yeah.ruisu.mvp_dagger.di.DaggerMainActivityComponent;
import com.yeah.ruisu.mvp_dagger.di.MainActivityModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
    implements MainActivityContract.View
{
    public static final String TAG = "MainActivityTag";

    @Inject
    MainActivityPresenter presenter;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ((MyApp) getApplication()).getAppComponent().newMainActivityComponent(new MainActivityModule()).inject(this);
        presenter.attachView(this);
        presenter.setContext(this);
    }

    @Override
    public void isPersonSaved(boolean isSaved)
    {
        Log.d(TAG, "isPersonSaved: " +isSaved);

    }

    @Override
    public void sendPerson(String person)
    {
        Log.d(TAG, "sendPerson: " + person);
    }

    @Override
    public void showerror(String s)
    {
        Log.d(TAG, "showerror: " + s);
    }

    public void doMagic(View view)
    {
        presenter.savePerson("Cacatua Joe");
        presenter.getPerson();
    }
}
