package com.example.admin.deltachallenge.di.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.admin.deltachallenge.data.repository.Repository
import com.example.admin.deltachallenge.ui.MainViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) MainViewModel(repository) as T
        else throw IllegalArgumentException("ViewModel not Found")
    }


}