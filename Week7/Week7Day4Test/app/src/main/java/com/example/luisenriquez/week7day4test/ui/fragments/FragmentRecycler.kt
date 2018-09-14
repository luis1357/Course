package com.example.luisenriquez.week7day4test.ui.fragments

import android.app.Fragment
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luisenriquez.week7day4test.R
import com.example.luisenriquez.week7day4test.adapter.NumberRecyclerAdapter
import com.example.luisenriquez.week7day4test.databinding.FragmentRecyclerBinding
import com.example.luisenriquez.week7day4test.ui.activities.MainActivity
import com.example.luisenriquez.week7day4test.ui.activities.MainViewModel
import javax.inject.Inject

class FragmentRecycler : Fragment(), LifecycleOwner
{
    override fun getLifecycle(): Lifecycle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var view_Model: MainViewModel
    private lateinit var frgmntRcclrBinding: FragmentRecyclerBinding


    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View
    {

        frgmntRcclrBinding = DataBindingUtil.inflate(inflater!!,
                                                        R.layout.fragment_recycler,
                                                        container,
                                                        false)


        var view = frgmntRcclrBinding.getRoot()

        return view
    }
/*
    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

        view_Model= ViewModelProviders.of(activity!!).get(MainViewModel::class.java)

        view_Model.getRandomNumbers().observe(this, Observer {
            frgmntRcclrBinding.rvFragment.apply{
                adapter = it
                layoutManager = LinearLayoutManager(this@FragmentRecycler)
            }
        })

    }*/
}

