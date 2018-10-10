package com.example.admin.deltachallenge.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.deltachallenge.adapters.RandomNumbersAdapter
import com.example.admin.deltachallenge.databinding.RecyclerFragmentBinding
import com.example.admin.deltachallenge.di.fragment.FragmentModule
import javax.inject.Inject

class RecyclerViewFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var communicatorViewModel: CommunicatorViewModel

    lateinit var binding: RecyclerFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RecyclerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).activityComponent.newFragmentComponent(FragmentModule(this)).inject(this)
        setUpObservers()
    }

    private fun setUpObservers() {
        mainViewModel.getRandomNumbers().observe(this, Observer {
            binding.rvNumbers.apply {
                adapter = it?.let { numberList -> RandomNumbersAdapter(numberList, communicatorViewModel) }
                layoutManager = LinearLayoutManager(activity)
            }
        })
    }

}