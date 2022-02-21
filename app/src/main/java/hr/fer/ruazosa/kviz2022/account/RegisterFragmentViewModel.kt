package hr.fer.ruazosa.kviz2022.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.UserRegisterDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    val email = MutableLiveData<String>("")

    val password = MutableLiveData<String>("")

    val passwordRepeat = MutableLiveData<String>("")

    val userName = MutableLiveData<String>("")

    private val _registration = MutableLiveData<Boolean>()
    val registration: LiveData<Boolean> get() = _registration

    fun register(){
        viewModelScope.launch {
            val form = UserRegisterDTO(
                email.value.toString(),
                userName.value.toString(),
                password.value.toString(),
                passwordRepeat.value.toString())
            val user = userRepository.registerAsync(form)
            _registration.value = user.succeeded
        }
    }
}