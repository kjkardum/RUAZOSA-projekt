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

    private val _navigateNext = MutableLiveData<Boolean>()
    val gotoNext: LiveData<Boolean> get() = _navigateNext

    fun next(){
        Timber.d("idk")
        _navigateNext.value = true
    }
    fun doneNext(){
        _navigateNext.value = false
    }
}