package com.yeah.ruisu.week4day13.mymainactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yeah.ruisu.week4day13.base.BaseFragment;
import com.yeah.ruisu.week4day13.R;

public final class MyMainActivityFragment extends BaseFragment implements MyMainActivityContract.View {

    private MyMainActivityContract.Presenter mPresenter;

    // Your presenter is available using the mPresenter variable
    public MyMainActivityFragment() {
        // Required empty public constructor
    }

    public static MyMainActivityFragment newInstance() {
        return new MyMainActivityFragment();
    }

    @Override
    public void setPresenter(MyMainActivityContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_main_layout, container, false);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMyMainActivityFragmentInteractionListener {
        // TODO: Update argument type and name
        void onMyMainActivityFragmentInteraction();
    }

}
