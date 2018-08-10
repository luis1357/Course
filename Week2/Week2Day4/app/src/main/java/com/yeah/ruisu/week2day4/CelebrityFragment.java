package com.yeah.ruisu.week2day4;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class CelebrityFragment extends Fragment
{
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private TextView tvCelebName;
    private TextView tvCelebAlias;
    private TextView tvCelebRace;
    private TextView tvCelebNat;
    private TextView tvCelebProf;
    private TextView tvCelebBio;
    private ImageView ivPhoto;

    public static CelebrityFragment newInstance(String param1)
    {
        CelebrityFragment fragment = new CelebrityFragment();

        Bundle bundle = new Bundle();

        bundle.putString(ARG_PARAM1, param1);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        /* Inflate the layout for this fragment. */
        View view =inflater.inflate(R.layout.fragment_celebrity, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        tvCelebName = view.findViewById(R.id.tvName);
        tvCelebAlias = view.findViewById(R.id.tvAlias);
        tvCelebRace = view.findViewById(R.id.tvRace);
        tvCelebNat = view.findViewById(R.id.tvNat);
        tvCelebProf = view.findViewById(R.id.tvProf);
        tvCelebBio = view.findViewById(R.id.tvBio);
        ivPhoto = view.findViewById(R.id.ivCelebPht);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        getCelebInfo(mParam1);
    }

    public void getCelebInfo(String CelebName)
    {
        /* First we get the identifiers. */
        int NamResId = this.getResources().getIdentifier("@string/" + CelebName,
                                                            "string",
                                                            getActivity().getPackageName());
        int AlsResId = this.getResources().getIdentifier("@string/" + CelebName + "_Als",
                                                            "string",
                                                            getActivity().getPackageName());
        int RceResId = this.getResources().getIdentifier("@string/" + CelebName + "_Rce",
                                                            "string",
                                                            getActivity().getPackageName());
        int NatResId = this.getResources().getIdentifier("@string/" + CelebName + "_Nat",
                                                            "string",
                                                            getActivity().getPackageName());
        int PrfResId = this.getResources().getIdentifier("@string/" + CelebName + "_Prf",
                                                            "string",
                                                            getActivity().getPackageName());
        int BioResId = this.getResources().getIdentifier("@string/" + CelebName + "_Bio",
                                                            "string",
                                                            getActivity().getPackageName());
        int PhotoId = this.getResources().getIdentifier(CelebName,
                                                        "drawable",
                                                        getActivity().getPackageName());

        tvCelebName.setText(NamResId);
        tvCelebAlias.setText(AlsResId);
        tvCelebRace.setText(RceResId);
        tvCelebNat.setText(NatResId);
        tvCelebProf.setText(PrfResId);
        tvCelebBio.setText(BioResId);
        ivPhoto.setImageResource(PhotoId);

    }
}
