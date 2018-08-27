package com.yeah.ruisu.weekend4;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.yeah.ruisu.weekend4.models.PlacesData;
import com.yeah.ruisu.weekend4.remote.RemoteServiceHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView places;
    MyPlacesAdapter adapter;

    String usrLocation;

    private List<MyPlcsInfo> plcsList;

    public static final String TAG = "MainActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usrLocation = getUserLocation();

        places = findViewById(R.id.auTextView);
        adapter = new MyPlacesAdapter(this, usrLocation);
        places.setAdapter(adapter);

        /* Text changed listener to get results precisely according to our search. */
        places.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                /* Calling getfilter to filter the results. */
                adapter.getFilter().filter(s);

                /* Notify the adapters after results changed. */
                adapter.notifyDataSetChanged();

                try {
                    RequestPlacesData(s.toString(), usrLocation);
                } catch (Exception e) {
                    Log.d(TAG, "onTextChanged: " + e.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        /* Handling click of autotextcompleteview items. */
        places.setOnItemClickListener((parent, view, position, id) ->
        {
            MyGooglePlaces googlePlaces = (MyGooglePlaces) parent.getItemAtPosition(position);
            places.setText(googlePlaces.getName());

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater myInflater = getMenuInflater();
        myInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return true;
        //return super.onOptionsItemSelected(item);
    }

    @SuppressLint("CheckResult")
    public void RequestPlacesData(String SearchPlace, String usrLoc)
    {
        RemoteServiceHelper MyRmtSrvcHlpr = RemoteServiceHelper.getINSTANCE();

        MyRmtSrvcHlpr.getPlacesData(SearchPlace, usrLoc)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(data ->
                {
                    runOnUiThread(() ->
                    {
                        FillLayout(data);
                    });
                })
                .subscribe(data ->
                        {
                            Log.d(TAG, "makeCall: " +
                                    data.toString());
                        },
                        Throwable::printStackTrace);
    }

    public void FillLayout(PlacesData inPlcsData) {
        FillRecycler(inPlcsData);
    }

    public void FillRecycler(PlacesData inPlcsData) {
        plcsList = new ArrayList<>();
        String plcName, plcAddress, plcImgUrl;

        for (int i = 1; i < inPlcsData.getResults().size(); i++) {
            plcName = inPlcsData.getResults().get(i).getName();
            plcAddress = inPlcsData.getResults().get(i).getVicinity();
            plcImgUrl = inPlcsData.getResults().get(i).getIcon();

            plcsList.add(new MyPlcsInfo(plcName, plcAddress, plcImgUrl));
        }

        RecyclerView MyRecyclerView = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        MyRecyclerAdapter MyFrcstRcyclrAdptr = new MyRecyclerAdapter(plcsList);

        MyRecyclerView.setLayoutManager(layoutManager);
        MyRecyclerView.setAdapter(MyFrcstRcyclrAdptr);
        MyRecyclerView.setItemAnimator(itemAnimator);
    }

    public String getUserLocation()
    {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                                                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                                                    Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)
        {
            return null;
        }
        else
        {

            assert lm != null;
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            return String.valueOf(location.getLatitude()) +
                    "," +
                    String.valueOf(location.getLongitude());
        }
    }
}
