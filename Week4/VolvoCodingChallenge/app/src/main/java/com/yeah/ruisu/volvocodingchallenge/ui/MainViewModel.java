package com.yeah.ruisu.volvocodingchallenge.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.util.Log;


import com.yeah.ruisu.volvocodingchallenge.AppController;
import com.yeah.ruisu.volvocodingchallenge.data.RepositoryModule.Repository;
import com.yeah.ruisu.volvocodingchallenge.data.RepositoryModule.RepositoryImpl;
import com.yeah.ruisu.volvocodingchallenge.data.remote.models.ForecastDatum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends AndroidViewModel
{
    public static final String TAG = "MainViewModel: ";

    @Inject
    Repository myRepo;

    private MutableLiveData<String> cstCityName = new MutableLiveData<>();
    private MutableLiveData<String> cstCtyStatus = new MutableLiveData<>();
    private MutableLiveData<String> cstCtyExpctd = new MutableLiveData<>();
    private MutableLiveData<String> cstCtyHgh = new MutableLiveData<>();
    private MutableLiveData<String> cstCtyLw = new MutableLiveData<>();

    private MutableLiveData<String> stckCityName = new MutableLiveData<>();
    private MutableLiveData<String> stckCtyStatus = new MutableLiveData<>();
    private MutableLiveData<String> stckCtyExpctd = new MutableLiveData<>();
    private MutableLiveData<String> stckCtyHgh = new MutableLiveData<>();
    private MutableLiveData<String> stckCtyLw = new MutableLiveData<>();

    private MutableLiveData<String> mntnCityName = new MutableLiveData<>();
    private MutableLiveData<String> mntnCtyStatus = new MutableLiveData<>();
    private MutableLiveData<String> mntnCtyExpctd = new MutableLiveData<>();
    private MutableLiveData<String> mntnCtyHgh = new MutableLiveData<>();
    private MutableLiveData<String> mntnCtyLw = new MutableLiveData<>();

    private MutableLiveData<String> lndnCityName = new MutableLiveData<>();
    private MutableLiveData<String> lndnCtyStatus = new MutableLiveData<>();
    private MutableLiveData<String> lndnCtyExpctd = new MutableLiveData<>();
    private MutableLiveData<String> lndnCtyHgh = new MutableLiveData<>();
    private MutableLiveData<String> lndnCtyLw = new MutableLiveData<>();

    private MutableLiveData<String> nwYrkCityName = new MutableLiveData<>();
    private MutableLiveData<String> nwYrkCtyStatus = new MutableLiveData<>();
    private MutableLiveData<String> nwYrkCtyExpctd = new MutableLiveData<>();
    private MutableLiveData<String> nwYrkCtyHgh = new MutableLiveData<>();
    private MutableLiveData<String> nwYrkCtyLw = new MutableLiveData<>();

    private MutableLiveData<String> brlnCityName = new MutableLiveData<>();
    private MutableLiveData<String> brlnCtyStatus = new MutableLiveData<>();
    private MutableLiveData<String> brlnCtyExpctd = new MutableLiveData<>();
    private MutableLiveData<String> brlnCtyHgh = new MutableLiveData<>();
    private MutableLiveData<String> brlnCtyLw = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application)
    {
        super(application);

        ((AppController) application).getAppComponent().inject(this);
    }

    public LiveData<List<ForecastDatum>> getGothWeatherForecast()
    {
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);

        int yearI = cal.get(Calendar.YEAR);
        int monthI = cal.get(Calendar.MONTH) + 1;
        int dayI = cal.get(Calendar.DAY_OF_MONTH);

        String year, month, day;

        year = String.valueOf(yearI);
        month = String.valueOf(monthI);
        day = String.valueOf(dayI);

        myRepo.getWeatherForecast("890869", year, month, day);

        return Transformations.map(((RepositoryImpl) myRepo)
                        .getWeatherForecastLiveData(),
                input ->
                {
                    Log.d(TAG, "getWeatherForecast: " +
                            input.get(0).getTheTemp());

                    cstCityName.setValue("Gothenburg");
                    cstCtyStatus.setValue(input.get(0)
                            .getWeatherStateName());
                    cstCtyExpctd.setValue(input.get(0)
                            .getTheTemp().toString());
                    cstCtyHgh.setValue(input.get(0)
                            .getMaxTemp().toString());
                    cstCtyLw.setValue(input.get(0)
                            .getMinTemp().toString());

                    return input;
                });
    }

    public LiveData<List<ForecastDatum>> getStockWeatherForecast()
    {
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);

        int yearI = cal.get(Calendar.YEAR);
        int monthI = cal.get(Calendar.MONTH) + 1;
        int dayI = cal.get(Calendar.DAY_OF_MONTH);

        String year, month, day;

        year = String.valueOf(yearI);
        month = String.valueOf(monthI);
        day = String.valueOf(dayI);

        myRepo.getWeatherForecast("906057", year, month, day);

        return Transformations.map(((RepositoryImpl) myRepo)
                                    .getWeatherForecastLiveData(),
                input ->
                {
                    Log.d(TAG, "getWeatherForecast: " +
                            input.get(0).getTheTemp());

                    stckCityName.setValue("StockHolm");
                    stckCtyStatus.setValue(input.get(0)
                                                .getWeatherStateName());
                    stckCtyExpctd.setValue(input.get(0)
                                            .getTheTemp().toString());
                    stckCtyHgh.setValue(input.get(0)
                                            .getMaxTemp().toString());
                    stckCtyLw.setValue(input.get(0)
                                            .getMinTemp().toString());

                    return input;
                });
    }

    public LiveData<List<ForecastDatum>> getMntnVwkWeatherForecast()
    {
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);

        int yearI = cal.get(Calendar.YEAR);
        int monthI = cal.get(Calendar.MONTH) + 1;
        int dayI = cal.get(Calendar.DAY_OF_MONTH);

        String year, month, day;

        year = String.valueOf(yearI);
        month = String.valueOf(monthI);
        day = String.valueOf(dayI);

        myRepo.getWeatherForecast("2455920", year, month, day);

        return Transformations.map(((RepositoryImpl) myRepo)
                        .getWeatherForecastLiveData(),
                input ->
                {
                    Log.d(TAG, "getWeatherForecast: " +
                            input.get(0).getTheTemp());

                    mntnCityName.setValue("Mountain View");
                    mntnCtyStatus.setValue(input.get(0)
                            .getWeatherStateName());
                    mntnCtyExpctd.setValue(input.get(0)
                            .getTheTemp().toString());
                    mntnCtyHgh.setValue(input.get(0)
                            .getMaxTemp().toString());
                    mntnCtyLw.setValue(input.get(0)
                            .getMinTemp().toString());

                    return input;
                });
    }

    public LiveData<List<ForecastDatum>> getLondonWeatherForecast()
    {
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);

        int yearI = cal.get(Calendar.YEAR);
        int monthI = cal.get(Calendar.MONTH) + 1;
        int dayI = cal.get(Calendar.DAY_OF_MONTH);

        String year, month, day;

        year = String.valueOf(yearI);
        month = String.valueOf(monthI);
        day = String.valueOf(dayI);

        myRepo.getWeatherForecast("44418", year, month, day);

        return Transformations.map(((RepositoryImpl) myRepo)
                        .getWeatherForecastLiveData(),
                input ->
                {
                    Log.d(TAG, "getWeatherForecast: " +
                            input.get(0).getTheTemp());

                    lndnCityName.setValue("London");
                    lndnCtyStatus.setValue(input.get(0)
                            .getWeatherStateName());
                    lndnCtyExpctd.setValue(input.get(0)
                            .getTheTemp().toString());
                    lndnCtyHgh.setValue(input.get(0)
                            .getMaxTemp().toString());
                    lndnCtyLw.setValue(input.get(0)
                            .getMinTemp().toString());

                    return input;
                });
    }

    public LiveData<List<ForecastDatum>> getNwYrkWeatherForecast()
    {
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);

        int yearI = cal.get(Calendar.YEAR);
        int monthI = cal.get(Calendar.MONTH) + 1;
        int dayI = cal.get(Calendar.DAY_OF_MONTH);

        String year, month, day;

        year = String.valueOf(yearI);
        month = String.valueOf(monthI);
        day = String.valueOf(dayI);

        myRepo.getWeatherForecast("2459115", year, month, day);

        return Transformations.map(((RepositoryImpl) myRepo)
                        .getWeatherForecastLiveData(),
                input ->
                {
                    Log.d(TAG, "getWeatherForecast: " +
                            input.get(0).getTheTemp());

                    nwYrkCityName.setValue("London");
                    nwYrkCtyStatus.setValue(input.get(0)
                            .getWeatherStateName());
                    nwYrkCtyExpctd.setValue(input.get(0)
                            .getTheTemp().toString());
                    nwYrkCtyHgh.setValue(input.get(0)
                            .getMaxTemp().toString());
                    nwYrkCtyLw.setValue(input.get(0)
                            .getMinTemp().toString());

                    return input;
                });
    }

    public LiveData<List<ForecastDatum>> getBrlnWeatherForecast()
    {
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);

        int yearI = cal.get(Calendar.YEAR);
        int monthI = cal.get(Calendar.MONTH) + 1;
        int dayI = cal.get(Calendar.DAY_OF_MONTH);

        String year, month, day;

        year = String.valueOf(yearI);
        month = String.valueOf(monthI);
        day = String.valueOf(dayI);

        myRepo.getWeatherForecast("638242", year, month, day);

        return Transformations.map(((RepositoryImpl) myRepo)
                        .getWeatherForecastLiveData(),
                input ->
                {
                    Log.d(TAG, "getWeatherForecast: " +
                            input.get(0).getTheTemp());

                    brlnCityName.setValue("London");
                    brlnCtyStatus.setValue(input.get(0)
                            .getWeatherStateName());
                    brlnCtyExpctd.setValue(input.get(0)
                            .getTheTemp().toString());
                    brlnCtyHgh.setValue(input.get(0)
                            .getMaxTemp().toString());
                    brlnCtyLw.setValue(input.get(0)
                            .getMinTemp().toString());

                    return input;
                });
    }

    public MutableLiveData<String> getMntnCityName() {
        return mntnCityName;
    }

    public void setMntnCityName(MutableLiveData<String> mntnCityName) {
        this.mntnCityName = mntnCityName;
    }

    public MutableLiveData<String> getMntnCtyStatus() {
        return mntnCtyStatus;
    }

    public void setMntnCtyStatus(MutableLiveData<String> mntnCtyStatus) {
        this.mntnCtyStatus = mntnCtyStatus;
    }

    public MutableLiveData<String> getMntnCtyExpctd() {
        return mntnCtyExpctd;
    }

    public void setMntnCtyExpctd(MutableLiveData<String> mntnCtyExpctd) {
        this.mntnCtyExpctd = mntnCtyExpctd;
    }

    public MutableLiveData<String> getMntnCtyHgh() {
        return mntnCtyHgh;
    }

    public void setMntnCtyHgh(MutableLiveData<String> mntnCtyHgh) {
        this.mntnCtyHgh = mntnCtyHgh;
    }

    public MutableLiveData<String> getMntnCtyLw() {
        return mntnCtyLw;
    }

    public void setMntnCtyLw(MutableLiveData<String> mntnCtyLw) {
        this.mntnCtyLw = mntnCtyLw;
    }

    public MutableLiveData<String> getLndnCityName() {
        return lndnCityName;
    }

    public void setLndnCityName(MutableLiveData<String> lndnCityName) {
        this.lndnCityName = lndnCityName;
    }

    public MutableLiveData<String> getLndnCtyStatus() {
        return lndnCtyStatus;
    }

    public void setLndnCtyStatus(MutableLiveData<String> lndnCtyStatus) {
        this.lndnCtyStatus = lndnCtyStatus;
    }

    public MutableLiveData<String> getLndnCtyExpctd() {
        return lndnCtyExpctd;
    }

    public void setLndnCtyExpctd(MutableLiveData<String> lndnCtyExpctd) {
        this.lndnCtyExpctd = lndnCtyExpctd;
    }

    public MutableLiveData<String> getLndnCtyHgh() {
        return lndnCtyHgh;
    }

    public void setLndnCtyHgh(MutableLiveData<String> lndnCtyHgh) {
        this.lndnCtyHgh = lndnCtyHgh;
    }

    public MutableLiveData<String> getLndnCtyLw() {
        return lndnCtyLw;
    }

    public void setLndnCtyLw(MutableLiveData<String> lndnCtyLw) {
        this.lndnCtyLw = lndnCtyLw;
    }

    public MutableLiveData<String> getNwYrkCityName() {
        return nwYrkCityName;
    }

    public void setNwYrkCityName(MutableLiveData<String> nwYrkCityName) {
        this.nwYrkCityName = nwYrkCityName;
    }

    public MutableLiveData<String> getNwYrkCtyStatus() {
        return nwYrkCtyStatus;
    }

    public void setNwYrkCtyStatus(MutableLiveData<String> nwYrkCtyStatus) {
        this.nwYrkCtyStatus = nwYrkCtyStatus;
    }

    public MutableLiveData<String> getNwYrkCtyExpctd() {
        return nwYrkCtyExpctd;
    }

    public void setNwYrkCtyExpctd(MutableLiveData<String> nwYrkCtyExpctd) {
        this.nwYrkCtyExpctd = nwYrkCtyExpctd;
    }

    public MutableLiveData<String> getNwYrkCtyHgh() {
        return nwYrkCtyHgh;
    }

    public void setNwYrkCtyHgh(MutableLiveData<String> nwYrkCtyHgh) {
        this.nwYrkCtyHgh = nwYrkCtyHgh;
    }

    public MutableLiveData<String> getNwYrkCtyLw() {
        return nwYrkCtyLw;
    }

    public void setNwYrkCtyLw(MutableLiveData<String> nwYrkCtyLw) {
        this.nwYrkCtyLw = nwYrkCtyLw;
    }

    public MutableLiveData<String> getBrlnCityName() {
        return brlnCityName;
    }

    public void setBrlnCityName(MutableLiveData<String> brlnCityName) {
        this.brlnCityName = brlnCityName;
    }

    public MutableLiveData<String> getBrlnCtyStatus() {
        return brlnCtyStatus;
    }

    public void setBrlnCtyStatus(MutableLiveData<String> brlnCtyStatus) {
        this.brlnCtyStatus = brlnCtyStatus;
    }

    public MutableLiveData<String> getBrlnCtyExpctd() {
        return brlnCtyExpctd;
    }

    public void setBrlnCtyExpctd(MutableLiveData<String> brlnCtyExpctd) {
        this.brlnCtyExpctd = brlnCtyExpctd;
    }

    public MutableLiveData<String> getBrlnCtyHgh() {
        return brlnCtyHgh;
    }

    public void setBrlnCtyHgh(MutableLiveData<String> brlnCtyHgh) {
        this.brlnCtyHgh = brlnCtyHgh;
    }

    public MutableLiveData<String> getBrlnCtyLw() {
        return brlnCtyLw;
    }

    public void setBrlnCtyLw(MutableLiveData<String> brlnCtyLw) {
        this.brlnCtyLw = brlnCtyLw;
    }

    public MutableLiveData<String> getStckCityName() {
        return stckCityName;
    }

    public void setStckCityName(MutableLiveData<String> stckCityName) {
        this.stckCityName = stckCityName;
    }

    public MutableLiveData<String> getStckCtyStatus() {
        return stckCtyStatus;
    }

    public void setStckCtyStatus(MutableLiveData<String> stckCtyStatus) {
        this.stckCtyStatus = stckCtyStatus;
    }

    public MutableLiveData<String> getStckCtyExpctd() {
        return stckCtyExpctd;
    }

    public void setStckCtyExpctd(MutableLiveData<String> stckCtyExpctd) {
        this.stckCtyExpctd = stckCtyExpctd;
    }

    public MutableLiveData<String> getStckCtyHgh() {
        return stckCtyHgh;
    }

    public void setStckCtyHgh(MutableLiveData<String> stckCtyHgh) {
        this.stckCtyHgh = stckCtyHgh;
    }

    public MutableLiveData<String> getStckCtyLw() {
        return stckCtyLw;
    }

    public void setStckCtyLw(MutableLiveData<String> stckCtyLw) {
        this.stckCtyLw = stckCtyLw;
    }

    public MutableLiveData<String> getCstCtyStatus()
    {
        return cstCtyStatus;
    }

    public void setCstCtyStatus(MutableLiveData<String> cstCtyStatus)
    {
        this.cstCtyStatus = cstCtyStatus;
    }

    public MutableLiveData<String> getCstCtyExpctd()
    {
        return cstCtyExpctd;
    }

    public void setCstCtyExpctd(MutableLiveData<String> cstCtyExpctd)
    {
        this.cstCtyExpctd = cstCtyExpctd;
    }

    public MutableLiveData<String> getCstCtyHgh()
    {
        return cstCtyHgh;
    }

    public void setCstCtyHgh(MutableLiveData<String> cstCtyHgh)
    {
        this.cstCtyHgh = cstCtyHgh;
    }

    public MutableLiveData<String> getCstCtyLw() {
        return cstCtyLw;
    }

    public void setCstCtyLw(MutableLiveData<String> cstCtyLw) {
        this.cstCtyLw = cstCtyLw;
    }

    public MutableLiveData<String> getCstCityName() {
        return cstCityName;
    }

    public void setCstCityName(MutableLiveData<String> cstCityName) {
        this.cstCityName = cstCityName;
    }
}
