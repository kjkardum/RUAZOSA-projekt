package hr.fer.ruazosa.kviz2022.game.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.FollowersRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameStartViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val followersRepository: FollowersRepository
) : ViewModel() {

    private val _selectedFollowers = MutableLiveData<List<GameUserDTO>>()
    val selectedFollowers: LiveData<List<GameUserDTO>> get() = _selectedFollowers

    private val _followers = MutableLiveData<List<GameUserDTO>>()
    val followers: LiveData<List<GameUserDTO>> get() = _followers

    private val _startGame = MutableLiveData<Boolean>()
    val startGame: LiveData<Boolean> get() = _startGame

    init {
        getFollowers()
    }

    private fun getFollowers(){
        viewModelScope.launch {
            _followers.value =  followersRepository.getFollowedUsers()
        }
    }

    fun startGame(){
        _startGame.value = true
    }

}