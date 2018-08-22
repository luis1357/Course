package com.yeah.ruisu.mvvmexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yeah.ruisu.mvvmexample.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, UserViewModel>
{
    @Override
    public UserViewModel onCreate()
    {
        return new UserViewModel(this);
    }

    @Override
    public int getVariable()
    {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_main;
    }
}
