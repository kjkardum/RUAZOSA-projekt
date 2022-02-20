package hr.fer.ruazosa.kviz2022.onboarding.screens

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor() : ViewModel(){

    private val _gotoNext = MutableLiveData<Boolean>()
    val gotoNext: LiveData<Boolean> get() = _gotoNext

    fun next(){
        _gotoNext.value = true
    }
    fun doneNext(){
        _gotoNext.value = false
    }
}