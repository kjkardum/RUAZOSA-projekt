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

    private val _get = MutableLiveData<Boolean>()
    val get: LiveData<Boolean> get() = _get

    private lateinit var _followers: List<GameUserDTO>

    init {
        _get.value = true
        getFollowers()
    }

    private fun getFollowers(){
        viewModelScope.launch {
            _followers =  followersRepository.getFollowedUsers()
        }
    }

    fun startGame(){

    }

}