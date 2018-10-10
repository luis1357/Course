package com.example.admin.deltachallenge.di.activity

import com.example.admin.deltachallenge.di.fragment.FragmentComponent
import com.example.admin.deltachallenge.di.fragment.FragmentModule
import com.example.admin.deltachallenge.ui.ItemFragment
import com.example.admin.deltachallenge.ui.MainActivity
import com.example.admin.deltachallenge.ui.RecyclerViewFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun newFragmentComponent(fragmentModule: FragmentModule): FragmentComponent

    fun inject(mainActivity: MainActivity)
}