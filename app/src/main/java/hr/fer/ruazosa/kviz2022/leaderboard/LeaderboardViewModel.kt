package hr.fer.ruazosa.kviz2022.leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.network.dto.game.GameLeaderboardResponseItemDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.FollowersRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    var followersRepository: FollowersRepository,
    var gameRepository: GameRepository,
    var userRepository: UserRepository
) : ViewModel() {
    private var _user = MutableLiveData<GameUserDTO>()
    val user: LiveData<GameUserDTO> get() = _user

    private var _leaderboardItems = MutableLiveData<List<GameLeaderboardResponseItemDTO>>()
    val leaderboardItems: LiveData<List<GameLeaderboardResponseItemDTO>> get() = _leaderboardItems

    init {
        getUser()
        getLeaderboard()
    }
    
    private fun getUser() {
        viewModelScope.launch {
            _user.value = userRepository.getUserWithUsername()
        }
    }

    fun followUser(user: GameLeaderboardResponseItemDTO) {
        viewModelScope.launch {
            followersRepository.addFollower(user.userId)
        }
    }

    fun getLeaderboard() {
        viewModelScope.launch {
            _leaderboardItems.value = gameRepository.getTotalLeaderboard()
        }
    }
}