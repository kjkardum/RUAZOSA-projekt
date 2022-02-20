package hr.fer.ruazosa.kviz2022.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.OnboardingActivity
import hr.fer.ruazosa.kviz2022.repository.interfaces.DemoDataRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.QuestionsRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val demoDataRepository: DemoDataRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _demoUserName = MutableLiveData<String>("")
    val demoUserName: LiveData<String> get() = _demoUserName

    private val _demoCounter = MutableLiveData<Int>(0)
    val demoCounter: LiveData<Int> get() = _demoCounter

    private val _achievements = MutableLiveData<List<String>>()
    val achievements: LiveData<List<String>> get() = _achievements

    private val _navigateToSomewhere = MutableLiveData<Boolean>()
    val navigateToSomewhere: LiveData<Boolean> get() = _navigateToSomewhere

    init {
        loadUser()

    }

    private fun loadUser() {
        viewModelScope.launch {
            val user = userRepository.authenticateAsync("admin@quizapp.com", "Pa\$\$w0rd")
            _demoUserName.value = userRepository.getUser()?.email
        }
    }

    fun navigate() {
        _navigateToSomewhere.value = true
        _demoCounter.value = _demoCounter.value?.plus(1)

    }
    fun doneNavigating() {
        _navigateToSomewhere.value = false
    }

    override fun onCleared() {
        Timber.i("HomepageViewModel cleared")
        super.onCleared()
    }

}