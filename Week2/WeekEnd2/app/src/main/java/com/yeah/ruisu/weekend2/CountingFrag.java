package com.yeah.ruisu.weekend2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class CountingFrag extends Fragment
{

    Timer T = new Timer();
    int count;

    private TextView tvCounter;

    TimerTask timer = new TimerTask()
    {
        @Override
        public void run()
        {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable()
            {
                @SuppressLint("SetTextI18n")
                @Override
                public void run()
                {
                    count++;
                    if(count >= 0)
                    {
                        tvCounter.setText(Integer.toString(count));
                    }
                }
            });

        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragmente_counter, container, false);

        tvCounter = view.findViewById(R.id.tvCounter);

        assert getArguments() != null;
        String Start = getArguments().getString("Strt");

        if("Start".equals(Start))
        {
            T = new Timer();

            T.scheduleAtFixedRate(timer, 0, 1000);
        }
        else
        {
            tvCounter.setText("0");
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        tvCounter = view.findViewById(R.id.tvCounter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onStop()
    {
        super.onStop();

        T.cancel();
    }
}
