package com.example.admin.multithreading;

import android.os.AsyncTask;
import android.util.Log;

public class TestAsyncTask extends AsyncTask<String, Integer, String>
{
    private static final String TAG = "AsyncTask: ";

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: " + Thread.currentThread());
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);

        Log.d(TAG, "onPostExecute: " + s + Thread.currentThread());
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + Thread.currentThread());
    }

    @Override
    protected String doInBackground(String... strings)
    {
        Log.d(TAG, "doInBackground: " + strings[0] + Thread.currentThread());

        publishProgress(1);
        return "result";
    }
}
