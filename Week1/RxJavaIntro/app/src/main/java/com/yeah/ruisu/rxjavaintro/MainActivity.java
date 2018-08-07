package com.yeah.ruisu.rxjavaintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView MyTextView;
    Button MyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Binding Views. */
        MyTextView = findViewById(R.id.tvNumbers);
        MyButton = findViewById(R.id.btnStartObservable);

        /* Creating observable. **/
        Observable<Integer> MyObservable = Observable.just(1, 2, 3, 4, 5);
        Observer<String> MyObserver = new Observer<String>()
        {
            @Override
            public void onCompleted()
            {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e)
            {
                Log.d(TAG, "onError: " + e.toString());
            }

            @Override
            public void onNext(String string)
            {
                Log.d(TAG, "onNext: " + string);
                MyTextView.setText(String.valueOf(string));
            }
        };

        MyObservable
                .subscribeOn(Schedulers.io())
                .delay(1, TimeUnit.SECONDS)
                .map(new Func1<Integer, String>()
                {
                    @Override
                    public String call(Integer MyInteger)
                    {
                        return MyInteger + " RxJava";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(MyObserver);
    }
}
