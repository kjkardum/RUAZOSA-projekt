package hr.fer.ruazosa.kviz2022.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.repository.interfaces.DemoDataRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val demoDataRepository: DemoDataRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _demoUserName = MutableLiveData<String>("")
    val demoUserName: LiveData<String> get() = _demoUserName

    private val _logoutAction = MutableLiveData<Boolean>()
    val logoutAction: LiveData<Boolean> get() = _logoutAction

    init {
    }

    fun logout() {
        userRepository.logoutUser()
        _logoutAction.value = true

    }
    fun finishLogout() {
        _logoutAction.value = false
    }

    override fun onCleared() {
        Timber.i("HomepageViewModel cleared")
        super.onCleared()
    }

}