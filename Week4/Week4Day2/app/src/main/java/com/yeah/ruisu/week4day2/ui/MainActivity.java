package com.yeah.ruisu.week4day2.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yeah.ruisu.week4day2.AppController;
import com.yeah.ruisu.week4day2.R;
import com.yeah.ruisu.week4day2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;
    private ActivityMainBinding myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUpViewModels();

        myBinder.setViewModel(mainViewModel);
        myBinder.setLifecycleOwner(this);
    }

    public void SearchUser(View view)
    {
        try
        {
            setUpObservers(myBinder.etSearcher.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void GoToRepos(View view) {
    }

    private void setUpViewModels()
    {
        mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
    }

    private void setUpObservers(String userName)
    {
        mainViewModel.getUserData(userName).observe(this,
                userData ->
                {
                    Log.d(TAG, "setUpObservers: ");
                    Glide.with(this)
                            .load(userData.getAvatarUrl())
                            .into(myBinder.ivUserProfileImg);

                });
    }
}
