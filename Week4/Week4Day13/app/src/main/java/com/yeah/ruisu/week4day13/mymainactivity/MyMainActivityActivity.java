package com.yeah.ruisu.week4day13.mymainactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yeah.ruisu.week4day13.base.BaseActivity;
import com.yeah.ruisu.week4day13.R;
import com.yeah.ruisu.week4day13.data.remote.RemoteServiceHelper;

import io.reactivex.schedulers.Schedulers;

//todo create BaseActivity and import to this class
public class MyMainActivityActivity extends BaseActivity
        implements MyMainActivityFragment.OnMyMainActivityFragmentInteractionListener
{

    public static final String TAG = "MainActivity: ";

    MyMainActivityContract.Presenter mPresenter;

    EditText etSearch;
    TextView tvUserName, tvLocation, tvHirable, tvRepos;
    ImageView ivPhoto;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_main_layout);

        MyMainActivityFragment MyMainActivityFragment = (MyMainActivityFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frame_layout_content);
        if (MyMainActivityFragment == null)
        {
            MyMainActivityFragment = MyMainActivityFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame_layout_content, MyMainActivityFragment);
            transaction.commit();
        }
        mPresenter = new MyMainActivityPresenter(this, MyMainActivityFragment);

        BindControls();
    }

    public void BindControls()
    {
        etSearch = findViewById(R.id.etSearcher);
        ivPhoto = findViewById(R.id.ivUserProfileImg);

        tvUserName = findViewById(R.id.tvUserName);
        tvLocation = findViewById(R.id.tvLocation);
        tvHirable = findViewById(R.id.tvHirable);
        tvRepos = findViewById(R.id.tvNmbrRepo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public void onMyMainActivityFragmentInteraction() {

    }

    public void SearchUser(View view)
    {
        RequestUserData();
    }

    @SuppressLint("CheckResult")
    public void RequestUserData ()
    {
        String UserN = etSearch.getText().toString();

        RemoteServiceHelper MyRmtSrvcHlpr = RemoteServiceHelper.getINSTANCE();

        MyRmtSrvcHlpr.getProfileData(UserN)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(data ->
                {
                    runOnUiThread(() ->
                    {
                        Toast.makeText(this,
                                data.getItems().get(0).getUrl(),
                                Toast.LENGTH_SHORT).show();

                    });
                })
                .subscribe(data ->
                        {
                            Log.d(TAG, "makeCall: " +
                                    data.getItems().get(0).getUrl());
                        },
                        Throwable::printStackTrace);
    }

}
