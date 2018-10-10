package com.example.admin.deltachallenge.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.admin.deltachallenge.data.repository.Repository

class MainViewModel(val repository: Repository) : ViewModel() {

    fun getRandomNumbers(): LiveData<List<Int>> {
        return Transformations.map(repository.getRandomNumbers()) {
            it.data.sort()
            it.data
        }
    }
}