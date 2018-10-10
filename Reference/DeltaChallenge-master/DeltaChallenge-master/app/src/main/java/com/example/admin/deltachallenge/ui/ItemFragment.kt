package com.example.admin.deltachallenge.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.deltachallenge.databinding.ItemFragmentBinding
import com.example.admin.deltachallenge.di.fragment.FragmentModule
import javax.inject.Inject

class ItemFragment : Fragment() {

    @Inject
    lateinit var communicatorViewModel: CommunicatorViewModel
    lateinit var binding: ItemFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ItemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity).activityComponent.newFragmentComponent(FragmentModule(this)).inject(this)
        binding.viewModel = communicatorViewModel
        binding.setLifecycleOwner(this)
    }
}