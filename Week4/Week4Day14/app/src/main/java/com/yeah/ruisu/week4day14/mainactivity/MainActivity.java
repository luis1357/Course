package com.yeah.ruisu.week4day14.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yeah.ruisu.week4day14.R;
import com.yeah.ruisu.week4day14.reposactivity.ReposActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private static final String TAG = "MainActivityTag";

    MainActivityPresenter presenter;

    Context context;

    EditText etSearch;
    TextView tvUserName, tvLocation, tvHirable, tvRepos;
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadPresenter();

        BindControls();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    public void LoadPresenter()
    {
        presenter = new MainActivityPresenter();
        presenter.attachView(this);
        presenter.setContext(context);
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
    public void showerror(String s) {
        Log.d(TAG, "showerror: ");
    }

    public void SearchUser(View view)
    {
        presenter.ShowUserName(etSearch.getText().toString());
    }


    @Override
    public void FillUserProfile (String Name, String Location,
                                 String Hirable, String RepositoriesN,
                                 String PicUrl)
    {
        tvUserName.setText(Name);
        tvLocation.setText(Location);
        tvHirable.setText(Hirable);
        tvRepos.setText(RepositoriesN);

        Picasso.with(this)
                .load(PicUrl)
                .fit()
                .into(ivPhoto);
    }

    public void GoToRepos(View view)
    {
        Intent RepoIntent = new Intent(this, ReposActivity.class);
        RepoIntent.putExtra("User", etSearch.getText().toString());

        startActivity(RepoIntent);
    }
}
