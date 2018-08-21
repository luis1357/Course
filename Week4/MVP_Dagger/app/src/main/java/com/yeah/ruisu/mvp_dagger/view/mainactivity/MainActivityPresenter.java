package com.yeah.ruisu.mvp_dagger.view.mainactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class MainActivityPresenter
    implements MainActivityContract.Presenter
{
    private static final String TAG = "MainPresenterTag: ";

    MainActivityContract.View view;
    Context context;

    @Override
    public void savePerson(String person)
    {
        Log.d(TAG, "savePerson: " + person);
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPref",
                                                                            Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("person", person);
        boolean isSaved = editor.commit();

        view.isPersonSaved(isSaved);
    }

    @Override
    public void getPerson()
    {
        String person;
        SharedPreferences sharedPreferences = context.getSharedPreferences("myPref",
                                                                            Context.MODE_PRIVATE);
        person = sharedPreferences.getString("person", "default person");

        view.sendPerson(person);
    }

    @Override
    public void setContext(Context context)
    {
        this.context = context;
    }

    @Override
    public void attachView(MainActivityContract.View view)
    {
        this.view = view;
    }

    @Override
    public void removeView()
    {
        this.view = null;
    }
}
