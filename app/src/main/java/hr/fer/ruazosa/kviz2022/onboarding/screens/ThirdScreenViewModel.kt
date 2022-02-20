package hr.fer.ruazosa.kviz2022.onboarding.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThirdScreenViewModel @Inject constructor() : ViewModel(){

    private val _gotoLogin = MutableLiveData<Boolean>()
    val gotoLogin: LiveData<Boolean> get() = _gotoLogin

    private val _gotoPrev = MutableLiveData<Boolean>()
    val gotoPrev: LiveData<Boolean> get() = _gotoPrev

    fun prev(){
        _gotoPrev.value = true
    }
    fun donePrev(){
        _gotoPrev.value = false
    }

    fun login(){
        _gotoLogin.value = true
    }
    fun doneLogin(){
        _gotoLogin.value = false
    }
}