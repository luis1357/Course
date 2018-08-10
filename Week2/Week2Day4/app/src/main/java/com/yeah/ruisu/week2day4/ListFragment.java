package com.yeah.ruisu.week2day4;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListFragment extends Fragment
{

    private OnFragmentInteractionListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        /* We get the list view. */
        final ListView lvCeleb = view.findViewById(R.id.lvClbrtyLst);

        lvCeleb.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int i, long l)
            {
                //String Celeb = (lvCeleb.getItemAtPosition(i)).toString();
                mListener.onFragmentInteraction(i);
            }
        });

        return view;
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

    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(int CelebId);
    }
}
