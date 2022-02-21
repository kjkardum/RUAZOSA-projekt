package hr.fer.ruazosa.kviz2022.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.FollowerDTO
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDto
import hr.fer.ruazosa.kviz2022.repository.interfaces.DemoDataRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val demoDataRepository: DemoDataRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _suggestedFollowers = MutableLiveData<List<GameUserDto>>()
    val suggestedFollowers: LiveData<List<GameUserDto>> get() = _suggestedFollowers

    private val _loggedInEmail = MutableLiveData<String>("")
    val loggedInEmail: LiveData<String> get() = _loggedInEmail

    private val _logoutAction = MutableLiveData<Boolean>()
    val logoutAction: LiveData<Boolean> get() = _logoutAction

    init {
        getUser()
        _suggestedFollowers.value = listOf(
            GameUserDto(
                id = 1,
                userName = "Demo",
                totalGamesPoints = 0,
                following = listOf(),
            )
        )
    }

    private fun getUser() {
        viewModelScope.launch {
            _loggedInEmail.value = userRepository.getUser()?.email
        }
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