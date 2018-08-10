package com.yeah.ruisu.week2test1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment
{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private String mParam1;
    private String mParam2;
    private String mParam3;

    private ListView lvCars;
    private List<Car> CarList;

    private TextView tvTest;

    public static ListFragment newInstance(String param1, String param2,
                                            String param3)
    {
        ListFragment fragment = new ListFragment();

        Bundle bundle = new Bundle();

        bundle.putString(ARG_PARAM1, param1);
        bundle.putString(ARG_PARAM2, param2);
        bundle.putString(ARG_PARAM3, param3);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if(getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        lvCars = view.findViewById(R.id.lvCarList);
        tvTest = view.findViewById(R.id.tvTest);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        CarList = new ArrayList<>();
        getCarInfo(mParam1, mParam2, mParam3);

        CarListAdapter MyCarLstAdptr = new CarListAdapter(getActivity(),
                R.layout.car_list_item, CarList);

        lvCars.setAdapter(MyCarLstAdptr);

        MyCarLstAdptr.notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    private void getCarInfo(String mParam1, String mParam2,
                            String mParam3)
    {
        CarList.add(new Car(mParam1, mParam2, mParam3));

        tvTest.setText(mParam1 + " " + mParam2 + " " + mParam3);
    }


}
