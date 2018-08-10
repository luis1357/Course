package com.yeah.ruisu.week2test1;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddCarFragment extends Fragment
{
    /* We prepare the listener to send the information. */
    private OnFragmentInteractionListener mListener;

    TextView CarNm;
    TextView CarTp;
    TextView CarYr;

    Button AddBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_addcar, container, false);

        CarNm = view.findViewById(R.id.etCarModel);
        CarTp = view.findViewById(R.id.etType);
        CarYr = view.findViewById(R.id.etYear);

        AddBtn = view.findViewById(R.id.btnAddCar);

        AddBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String StrCarNm, StrCarTp, StrCarYr;

                StrCarNm = CarNm.getText().toString();
                StrCarTp = CarTp.getText().toString();
                StrCarYr = CarYr.getText().toString();

                mListener.onFragmentInteraction(StrCarNm, StrCarTp,
                      StrCarYr);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() +
                    " must implement OnFragmentInteractionListener.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    /* This Listener will send the information. */
    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(String CarNm, String CarType, String CarYear);
    }
}
