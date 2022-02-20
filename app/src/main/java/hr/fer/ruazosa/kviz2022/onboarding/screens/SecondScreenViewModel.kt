package hr.fer.ruazosa.kviz2022.onboarding.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor() : ViewModel(){

    private val _gotoNext = MutableLiveData<Boolean>()
    val gotoNext: LiveData<Boolean> get() = _gotoNext

    private val _gotoPrev = MutableLiveData<Boolean>()
    val gotoPrev: LiveData<Boolean> get() = _gotoPrev

    fun prev(){
        _gotoPrev.value = true
    }
    fun donePrev(){
        _gotoPrev.value = false
    }

    fun next(){
        _gotoNext.value = true
    }
    fun doneNext(){
        _gotoNext.value = false
    }
}