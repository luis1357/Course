package com.yeah.ruisu.week4day14.reposactivity;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.yeah.ruisu.week4day14.R;
import com.yeah.ruisu.week4day14.Repos.Repo;
import com.yeah.ruisu.week4day14.mainactivity.MainActivityPresenter;

public class ReposActivity extends Activity
    implements ReposContract.View
{
    private static final String TAG = "ReposActivityTag";

    ReposPresenter presenter;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        LoadPresenter();

        String UserName = getIntent().getStringExtra("User");

        presenter.GetUserRepos(UserName);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();

    }

    @Override
    public void showerror(String s)
    {
        Log.d(TAG, "showerror: ");
    }

    public void LoadPresenter()
    {
        presenter = new ReposPresenter();
        presenter.attachView(this);
        presenter.setContext(context);
    }
}
