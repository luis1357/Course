package com.example.luisenriquez.week7day11.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.luisenriquez.week7day11.ActivityCallback;
import com.example.luisenriquez.week7day11.R;
import com.example.luisenriquez.week7day11.ui.fragments.ChatFragment;
import com.example.luisenriquez.week7day11.ui.fragments.CreateAccountFragment;
import com.example.luisenriquez.week7day11.ui.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity
    implements ActivityCallback
{

    private static final String TAG = "MainActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, LoginFragment.newInstance())
                .commit();
    }

    @Override
    public void openChat()
    {
        Log.d(TAG, "openChat: ");
        replaceFragment(ChatFragment.newInstance());
    }

    @Override
    public void openCreateAccount()
    {
        replaceFragment(CreateAccountFragment.newInstance());
    }

    @Override
    public void logout()
    {
        replaceFragment(LoginFragment.newInstance());
    }

    private void replaceFragment(Fragment fragment)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
