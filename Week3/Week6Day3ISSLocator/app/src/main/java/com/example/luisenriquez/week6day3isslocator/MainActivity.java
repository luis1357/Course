package com.example.luisenriquez.week6day3isslocator;

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
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.luisenriquez.week6day3isslocator.data.remote.RemoteServiceHelper;
import com.example.luisenriquez.week6day3isslocator.data.remote.models.ISSResponse;
import com.example.luisenriquez.week6day3isslocator.utils.ISSPass;
import com.example.luisenriquez.week6day3isslocator.utils.IssPassAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity: ";

    EditText etLat, etLon;

    private List<ISSPass> issList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindControls();

        Log.d(TAG, "onCreate: " + EpochToTime("1536217919"));
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        setLatLon();
    }

    public void bindControls() {
        etLat = findViewById(R.id.etLat);
        etLon = findViewById(R.id.etLon);
    }

    public void GetISSPasses(View view)
    {
        RequestISSPass(etLat.getText().toString(),
                etLon.getText().toString());
    }

    @SuppressLint("CheckResult")
    public void RequestISSPass(String lat, String lon) {
        RemoteServiceHelper MyRmtSrvcHlpr = RemoteServiceHelper.getINSTANCE();

        MyRmtSrvcHlpr.getISSResponse(lat, lon)
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
                                    data.getResponse().get(0).getDuration());
                        },
                        Throwable::printStackTrace);
    }

    public void FillLayout(ISSResponse data) {
        Log.d(TAG, "FillLayout: " + data.getResponse().size());
        FillIssList(data);


    }

    public void FillIssList(ISSResponse data) {
        issList = new ArrayList<>();

        String issTimeEpoch, issTimeFrmtd, issDuration;

        for (int i = 0; i < data.getResponse().size(); i++) {
            issTimeEpoch = GetIssTime(data, i);
            issDuration = GetIssDuration(data, i);

            Log.d(TAG, "FillIssList: " + issTimeEpoch);

            issTimeFrmtd = EpochToTime(issTimeEpoch);

            issList.add(new ISSPass(issDuration,
                    issTimeFrmtd));
        }

        RecyclerView myRecyclerView = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        IssPassAdapter myRcyclrAdptr = new IssPassAdapter(issList);

        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(myRcyclrAdptr);
        myRecyclerView.setItemAnimator(itemAnimator);
    }

    public String GetIssTime(ISSResponse data, int i) {
        return data.getResponse().get(i).getRisetime().toString();
    }

    public String GetIssDuration(ISSResponse data, int i) {
        return data.getResponse().get(i).getDuration().toString();
    }

    public String EpochToTime(String epochTime) {
        Date cnvrtdDt = new Date(Long.parseLong(epochTime) * 1000);

        return cnvrtdDt.toString();
    }

    public void setLatLon()
    {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        assert lm != null;
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        etLat.setText(String.valueOf(location.getLatitude()));
        etLon.setText(String.valueOf(location.getLongitude()));
    }

    public void RefreshLocation(View view)
    {
        setLatLon();
    }
}
