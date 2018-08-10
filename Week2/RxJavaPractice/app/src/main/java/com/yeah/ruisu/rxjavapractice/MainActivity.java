package com.yeah.ruisu.rxjavapractice;

import android.annotation.SuppressLint;
import android.arch.core.util.Function;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    TextView tvCount, tvTimer;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = findViewById(R.id.tvCount);
        tvTimer = findViewById(R.id.tvTimer);


        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(number -> Toast.makeText(this,
                                                    number + " mississippi's",
                                                    Toast.LENGTH_SHORT).show())
                .observeOn(Schedulers.io())
                .map(aLong -> aLong*2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {

                    }

                    @Override
                    public void onNext(Long aLong)
                    {
                        tvCount.setText(String.valueOf(aLong));
                    }

                    @Override
                    public void onError (Throwable e)
                    {

                    }

                    @Override
                    public void onComplete()
                    {
                        Toast.makeText(MainActivity.this, "Completed!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        Observable.zip(Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                Observable.interval(1, TimeUnit.SECONDS),
                (integer, aLong) -> integer + aLong)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long> ()
                {

                    @Override
                    public void onSubscribe(Disposable d)
                    {

                    }

                    @Override
                    public void onNext(Long integer)
                    {
                        tvTimer.setText(String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete()
                    {
                        Toast.makeText(MainActivity.this, "Completed!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
