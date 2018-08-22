package com.yeah.ruisu.mvvmexample;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.util.Log;

import org.json.JSONException;

public class UserViewModel extends BaseViewModel<MainActivity>
{
    public final ObservableField<String> firstName = new ObservableField<>();
    public final ObservableField<String> lastName = new ObservableField<>();
    public final ObservableField<String> favoriteAnimal = new ObservableField<>();
    public final ObservableField<String> age = new ObservableField<>();

    private Repo userRepo;

    public static final String TAG = "UserViewModel";

    public UserViewModel(MainActivity activity)
    {
        super(activity);

        userRepo = new UserRepo(activity);
    }

    @Override
    public void onStart()
    {

    }

    @Override
    public void onResume()
    {
        try
        {
            Person person = userRepo.getUser();

            if (!person.getFirstName().equals("no person found"))
            {
                firstName.set(person.getFirstName());
                lastName.set(person.getLastName());
                favoriteAnimal.set(person.getFavoriteAnimal());
                age.set(String.valueOf(person.getAge()));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause()
    {
        storeUser();
    }

    @Override
    public void onStop()
    {

    }

    @Override
    public void onDestroy()
    {

    }

    public void storeUser()
    {
        Person person = new Person(firstName.get(), lastName.get(),
                                    favoriteAnimal.get(), Integer.parseInt(age.get()));

        Log.d(TAG, "storeUser: " + person.toString());

        userRepo.saveUser(person);
    }
}
