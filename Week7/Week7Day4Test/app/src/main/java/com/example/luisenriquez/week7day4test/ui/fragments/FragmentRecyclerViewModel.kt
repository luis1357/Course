package com.example.luisenriquez.week7day4test.ui.fragments

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.example.luisenriquez.week7day4test.AppController
import com.example.luisenriquez.week7day4test.adapter.NumberRecyclerAdapter
import com.example.luisenriquez.week7day4test.data.repositorymodule.Repository
import com.example.luisenriquez.week7day4test.utils.SingleEventLiveData
import javax.inject.Inject

class FragmentRecyclerViewModel (val context: Application) : AndroidViewModel(context)
{
    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var singleEventLiveData: SingleEventLiveData<Boolean>

    init
    {
        //(context as AppController).getComponent().inject(this)
    }

    fun getRandomNumbers() : LiveData<NumberRecyclerAdapter>
    {
        MakeNumbersCall()
        singleEventLiveData.value = true

        return Transformations.map(repository.numberResponseLiveData)
        {
            list ->
            NumberRecyclerAdapter(list, context)
        }
    }

    fun MakeNumbersCall()
    {
        repository.getRandomNumbers()
    }
}