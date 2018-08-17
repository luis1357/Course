package com.yeah.ruisu.restcalls;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yeah.ruisu.restcalls.data.remote.RemoteServiceHelper;
import com.yeah.ruisu.restcalls.models.WeatherData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity";
    private NativeReceiver MyNatRcvr;

    TextView MyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyNatRcvr = new NativeReceiver();

        MyTextView = findViewById(R.id.tvMyTextView);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        IntentFilter MyIntentFilter = new IntentFilter();
        MyIntentFilter.addAction(Constants.NATIVE_RECEIVER_ACTION);
        registerReceiver(MyNatRcvr, MyIntentFilter);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        unregisterReceiver(MyNatRcvr);
    }

    @SuppressLint("SetTextI18n")
    public void makeCall(View view)
    {
        final OkHttpClient MyOkHtttpClnt = new OkHttpClient();

        switch (view.getId())
        {
            case R.id.btnNativeHttp:
                Intent MyIntent = new Intent(this, MyIntentService.class);

                MyIntent.putExtra(Constants.Key.URL,
                                    Constants.INTENT_SERVICE_BASE_URL);

                startService(MyIntent);
                break;

            case R.id.btnOkHttpsync:

                Request SyncRequest = new Request.Builder()
                                                    .url(Constants.PERSON_BASE_URL)
                                                    .build();
                new Thread(() -> {
                    try
                    {
                        Response MyRspns = MyOkHtttpClnt.newCall(SyncRequest).execute();
                        final JSONObject MyJSONObjct = new JSONObject(MyRspns.body().string());

                        runOnUiThread(() -> {
                            try {
                                Toast.makeText(MainActivity.this,
                                                MyJSONObjct.getString("name"),
                                                Toast.LENGTH_LONG).show();
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        });
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }).start();
                break;

            case R.id.btnOkHttpAsync:
                Request MyRequest = new Request.Builder()
                                                .url(Constants.PERSON_BASE_URL)
                                                .build();

                MyOkHtttpClnt.newCall(MyRequest).enqueue(new Callback()
                {
                    @Override
                    public void onFailure(Call call, IOException e)
                    {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response)
                            throws IOException
                    {
                        runOnUiThread(() ->
                        {
                            try {
                                Gson MyGSon = new Gson();
                                String PersonJSON = response.body().string();
                                Person MyPerson = MyGSon.fromJson(PersonJSON, Person.class);

                                Log.d(TAG, "onResponse: " + MyPerson.toString());
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                            Toast.makeText(MainActivity.this,
                                            String.valueOf(response.code()),
                                    Toast.LENGTH_SHORT).show();
                        });
                    }
                });
                break;

            case R.id.btnRetroFitSync:
                RemoteServiceHelper MyRmtSrvcHlpr = RemoteServiceHelper.getINSTANCE();

                Gson myGsn = new Gson();

                Single.fromCallable(() ->
                {
                    retrofit2.Response<ResponseBody> MyResponse = MyRmtSrvcHlpr
                                                                        .getWeatherData()
                                                                        .execute();

                    assert MyResponse.body() != null;
                    String myJSON = MyResponse.body().string();

                    WeatherData data = myGsn.fromJson(myJSON, WeatherData.class);

                    return data;
                }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess(data ->
                    {
                        Toast.makeText(MainActivity.this,
                                        "Success!",
                                        Toast.LENGTH_SHORT).show();
                    })
                .subscribe(data ->
                {
                    MyTextView.setText(data.getCity().getName() +
                                        " " +
                                        data.getCity().getPopulation());
                },
                Throwable::printStackTrace);

                break;

            case R.id.btnRetroFitASync:

                RemoteServiceHelper myRmtSrvcHlpr1 = RemoteServiceHelper.getINSTANCE();

                Gson gson1 = new Gson();


                myRmtSrvcHlpr1.getWeatherData().enqueue(new retrofit2.Callback<ResponseBody>()
                {
                    @Override
                    public void onResponse(retrofit2.Call<ResponseBody> call,
                                           retrofit2.Response<ResponseBody> response)
                    {
                        runOnUiThread(() ->
                        {
                            try
                            {
                                String jSon = response.body().string();
                                WeatherData data = gson1.fromJson(jSon, WeatherData.class);

                                Log.d(TAG, "onResponse: " + jSon);

                                Toast.makeText(MainActivity.this,
                                        data.getCity() +
                                        " " +
                                        data.getList().toString(), Toast.LENGTH_SHORT).show();

                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onFailure(retrofit2.Call<ResponseBody> call,
                                          Throwable t)
                    {

                    }
                });

                break;

            case R.id.btnRetroFitRx:

                RemoteServiceHelper remoteServiceHelper2 = RemoteServiceHelper.getINSTANCE();

                remoteServiceHelper2.getWeatherDataSingle()
                                        .subscribeOn(Schedulers.io())
                                        .doOnSuccess(data ->
                                        {
                                            runOnUiThread(() ->
                                            {
                                                Toast.makeText(this,
                                                        data.getCod(),
                                                        Toast.LENGTH_SHORT)
                                                        .show();
                                            });
                                        })
                                        .subscribe(data ->
                                        {
                                            Log.d(TAG, "makeCall: " +
                                                        data.getCity());
                                        },
                                            Throwable::printStackTrace);

                break;
        }
    }
}
