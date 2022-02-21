package hr.fer.ruazosa.kviz2022.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.UserRegisterDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterFragmentViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _email = MutableLiveData<String>("")
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>("")
    val password: LiveData<String> get() = _password

    private val _passwordRepeat = MutableLiveData<String>("")
    val passwordRepeat: LiveData<String> get() = _passwordRepeat

    private val _userName = MutableLiveData<String>("")
    val userName: LiveData<String> get() = _userName

    private val _registration = MutableLiveData<Boolean>()
    val registration: LiveData<Boolean> get() = _registration

    fun register(){
        viewModelScope.launch {
            val form = UserRegisterDTO(_email.value.toString(), _userName.value.toString(), _password.value.toString(), _passwordRepeat.value.toString())
            val user = userRepository.registerAsync(form)
            _registration.value = user.succeeded
        }
    }
}