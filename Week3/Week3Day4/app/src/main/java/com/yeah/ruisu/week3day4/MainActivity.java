package com.yeah.ruisu.week3day4;

import android.annotation.SuppressLint;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.yeah.ruisu.week3day4.data.remote.RemoteServiceHelper;
import com.yeah.ruisu.week3day4.models.WeatherData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
{
    public static final String TAG = "MainActivity: ";

    ImageView ivWeather;
    EditText etZipCode;
    TextView tvCurrentCity, tvCurrentWeather,
                tvCrntHigh, tvCrntLow;

    private List<Forecast> ForeList;

    private DatabaseHelper MyDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindControls();
    }

    public void BindControls()
    {
        etZipCode = findViewById(R.id.etCity);
        ivWeather = findViewById(R.id.ivWeather);

        tvCurrentWeather = findViewById(R.id.tvTemperature);
        tvCurrentCity = findViewById(R.id.tvCity);
        tvCrntHigh = findViewById(R.id.tvCrntHigh);
        tvCrntLow = findViewById(R.id.tvCrntLow);
    }

    /* This little function will make sure we use the right weather icon. */
    public int SetWeatherIcon(WeatherData InWeather, int Day)
    {
        String WCode = GetWeatherIconID(InWeather, Day);

        char[] charArray = WCode.toCharArray();

        String FirstChar = String.valueOf(charArray[0]);
        String SecndChar = String.valueOf(charArray[1]);
        String ThirdChar = String.valueOf(charArray[2]);

        switch (FirstChar)
        {
            case "2":
                return R.drawable.w11;

            case "3":
                return R.drawable.w09;

            case "5":
                switch (SecndChar)
                {
                    case "0":
                        return R.drawable.w10;

                    case "1":
                        return R.drawable.w13;

                    default:
                        return R.drawable.w09;
                }

            case "6":
                return R.drawable.w13;


            case "7":
                return R.drawable.w50;


            case "8":
                switch (ThirdChar)
                {
                    case "0":
                        return R.drawable.w01;


                    case "1":
                        return R.drawable.w02;


                    case "2":
                        return R.drawable.w03;


                    default:
                        return R.drawable.w04;

                }


            default:
                return R.drawable.w01;

        }
    }

    public void SearchZip(View view)
    {
        RequestWeather();
    }

    @SuppressLint("CheckResult")
    public void RequestWeather ()
    {
        String Zip = etZipCode.getText().toString();
        String Units = "metric";

        MyDataHelper = new DatabaseHelper(this);

        RemoteServiceHelper MyRmtSrvcHlpr = RemoteServiceHelper.getINSTANCE();

        MyRmtSrvcHlpr.getWeatherData(Zip, Units)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(data ->
                {
                    runOnUiThread(() ->
                    {
                        Toast.makeText(this,
                                        data.getList().get(0).getMain().getTemp().toString(),
                                        Toast.LENGTH_SHORT).show();

                        FillLayout(data);

                        MyDataHelper.InsertData(data.toString());

                    });
                })
                .subscribe(data ->
                        {
                            Log.d(TAG, "makeCall: " +
                                    data.getList().toString());
                        },
                        Throwable::printStackTrace);
    }

    public void FillLayout (WeatherData InWeather)
    {
        FillCurrentWeather(InWeather);

        FillForecast(InWeather);
    }

    @SuppressLint("SetTextI18n")
    public void FillCurrentWeather(WeatherData InWeather)
    {
        tvCurrentCity.setText(InWeather.getCity().getName());
        tvCurrentWeather.setText(InWeather.getList().get(0).getMain().getTemp().toString() +
                                    " °C");
        tvCrntHigh.setText("High: " +
                            GetHighTemp(InWeather,0) +
                            " °C");
        tvCrntLow.setText("Low: " +
                            GetMinTemp(InWeather,0) +
                            " °C");

        ivWeather.setImageResource(SetWeatherIcon(InWeather, 0));
    }

    public String GetWeatherIconID (WeatherData InWeather, int Day)
    {
        return InWeather.getList().get(Day).getWeather().get(0).getId().toString();
    }

    public void FillForecast(WeatherData InWeather)
    {
        ForeList = new ArrayList<>();
        String MaxWeather, MinWeather;
        int ImageId;

        for (int i = 1; i < 6; i++)
        {
            ImageId = SetWeatherIcon(InWeather, i);
            MaxWeather = GetHighTemp(InWeather, i);
            MinWeather = GetMinTemp(InWeather, i);

            ForeList.add(new Forecast(ImageId, MaxWeather, MinWeather));
        }

        RecyclerView MyRecyclerView = findViewById(R.id.rvMain);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        ForecastRecyclerAdapter MyFrcstRcyclrAdptr = new ForecastRecyclerAdapter(ForeList);

        MyRecyclerView.setLayoutManager(layoutManager);
        MyRecyclerView.setAdapter(MyFrcstRcyclrAdptr);
        MyRecyclerView.setItemAnimator(itemAnimator);
    }

    public String GetHighTemp (WeatherData InWeather, int Day)
    {
        return InWeather.getList().get(Day).getMain().getTempMax().toString();
    }

    public String GetMinTemp (WeatherData InWeather, int Day)
    {
        return InWeather.getList().get(Day).getMain().getTempMin().toString();
    }

}

