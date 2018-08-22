package com.yeah.ruisu.mvvmexample;

import org.json.JSONException;

public interface Repo
{
    Person getUser() throws JSONException;

    void saveUser(Person person);
}
