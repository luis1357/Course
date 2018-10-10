package com.example.admin.deltachallenge.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.example.admin.deltachallenge.AppController
import com.example.admin.deltachallenge.data.repository.Repository
import javax.inject.Inject

class CommunicatorViewModel(context: Application) : AndroidViewModel(context) {

    @Inject
    lateinit var pickedNumber: MutableLiveData<Int>

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var displayedNumber: MediatorLiveData<String>

    init {
        (context as AppController).getComponent().inject(this)
        displayedNumber.apply {

            addSource(repository.averageLiveData) {
                this.value = it.toString()
            }

            addSource(pickedNumber) {
                this.value = it?.toString()
            }
        }
    }
}