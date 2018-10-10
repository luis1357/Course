package com.example.admin.deltachallenge

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.example.admin.deltachallenge.data.models.NumbersResponse
import com.example.admin.deltachallenge.data.repository.Repository
import com.example.admin.deltachallenge.ui.MainViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.mockito.ArgumentCaptor
import org.mockito.Spy

/**
 * Here we check whether the MainViewModel sorts the livedata object.
 */

@RunWith(MockitoJUnitRunner::class)
class MainViewModelUnitTest {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var observer: Observer<List<Int>>
    @Mock
    lateinit var repository: Repository
    @Spy
    lateinit var numberResponseLiveData: MutableLiveData<NumbersResponse>
    lateinit var mainViewModel: MainViewModel
    lateinit var numbersResponse: NumbersResponse

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(repository)
        numbersResponse = NumbersResponse()
        numbersResponse.data = (1..40).toMutableList().asReversed()
        numberResponseLiveData.value = numbersResponse
    }

    @Test
    fun listLiveData_isUpdated() {
        `when`(repository.getRandomNumbers()).thenReturn(numberResponseLiveData)
        mainViewModel.getRandomNumbers().observeForever(observer)
        verify(observer).onChanged((1..40).toList())
    }

    @After
    fun tearDown() {
        mainViewModel.getRandomNumbers().removeObserver(observer)
    }
}