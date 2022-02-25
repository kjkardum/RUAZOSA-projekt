package hr.fer.ruazosa.kviz2022.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.*
import hr.fer.ruazosa.kviz2022.network.dto.game.GameDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.DemoDataRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.FollowersRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val followersRepository: FollowersRepository,
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _suggestedFollowers = MutableLiveData<List<GameUserDTO>>()
    val suggestedFollowers: LiveData<List<GameUserDTO>> get() = _suggestedFollowers

    private val _loggedInEmail = MutableLiveData<String>("")
    val loggedInEmail: LiveData<String> get() = _loggedInEmail

    private val _logoutAction = MutableLiveData<Boolean>()
    val logoutAction: LiveData<Boolean> get() = _logoutAction

    private val _games = MutableLiveData<List<GameDTO>>()
    val games: LiveData<List<GameDTO>> get() = _games

    private val _continueGame = MutableLiveData<Boolean>()
    val continueGame: LiveData<Boolean> get() = _continueGame

    private val _continueGameId = MutableLiveData<Int>()
    val continueGameId: LiveData<Int> get() = _continueGameId

    init {
        getUser()
        if (!_loggedInEmail.value.isNullOrEmpty()) {
            getSuggestedFollowers()
        }
        //continueStartedGames()
    }

    private fun continueStartedGames(){
        viewModelScope.launch {
            _games.value = gameRepository.getActiveGames()
        }
    }

    private fun getSuggestedFollowers() {
        viewModelScope.launch {
            _suggestedFollowers.value = followersRepository.followSuggestions()
        }
    }

    fun followUser(user: GameUserDTO) {
        viewModelScope.launch {
            followersRepository.addFollower(user.id)
            getSuggestedFollowers()
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            _loggedInEmail.value = userRepository.getUser()?.email
        }
    }

    fun continueGame(game: GameDTO){
        Timber.d("continuing game!")
        _continueGameId.value = game.id
        _continueGame.value = true
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