package com.example.admin.deltachallenge.di.fragment

import com.example.admin.deltachallenge.di.factories.MainViewModelFactory
import com.example.admin.deltachallenge.ui.ItemFragment
import com.example.admin.deltachallenge.ui.MainViewModel
import com.example.admin.deltachallenge.ui.RecyclerViewFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(recyclerViewFragment: RecyclerViewFragment)

    fun inject(itemFragment: ItemFragment)

    fun mainViewModelFactory(): MainViewModelFactory
}