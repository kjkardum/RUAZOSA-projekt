package hr.fer.ruazosa.kviz2022.account

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.homepage.HomepageViewModel
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.ResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.UserLoginDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private val _errors = MutableLiveData<String>()
    val errors: LiveData<String> get() = _errors

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private val _logged = MutableLiveData<Boolean>()
    val logged: LiveData<Boolean> get() = _logged

    private val _register = MutableLiveData<Boolean>()
    val register: LiveData<Boolean> get() = _register

    fun loginUser(){
        viewModelScope.launch {
            val user = userRepository.authenticateAsync(email.value?:"", password.value?:"")
            if (!user.succeeded){
                Timber.d("errors")
                _errors.value = user?.message ?: ""
                Timber.d(_errors.value);
            }
            _logged.value = user.succeeded
        }
    }

    fun navigateToRegistration(){
        _register.value = true
    }
    fun doneNavigating(){
        _register.value = false
    }
}