package hr.fer.ruazosa.kviz2022.game.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.network.dto.UserDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.FollowersRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameStartViewModel @Inject constructor(
    private var gameRepository: GameRepository,
    private var followersRepository: FollowersRepository,
    private var userRepository: UserRepository
) : ViewModel() {

    private val _selectedFollowers = MutableLiveData<List<GameUserDTO>>()
    val selectedFollowers: LiveData<List<GameUserDTO>> get() = _selectedFollowers

    private val _followers = MutableLiveData<List<GameUserDTO>>()
    val followers: LiveData<List<GameUserDTO>> get() = _followers

    private val _startGame = MutableLiveData<Boolean>()
    val startGame: LiveData<Boolean> get() = _startGame

    private var addedFollowers: MutableList<Int>? = mutableListOf()

    init {
        getFollowers()
    }

    private fun getFollowers(){
        viewModelScope.launch {
            _followers.value =  followersRepository.getFollowedUsers()
        }
    }

    private fun addFollowerToList(user: GameUserDTO){
        viewModelScope.launch {
            addedFollowers?.add(user.id)
        }
    }

    private fun removeFollowerFromList(user: GameUserDTO){
        viewModelScope.launch {
            addedFollowers?.remove(user.id)
        }
    }

    private fun isFollowerOnList(user: GameUserDTO): Boolean?{
        return addedFollowers?.contains(user.id)
    }

    fun startGame(){
        viewModelScope.launch {
            addedFollowers?.add(userRepository.getUserWithUsername().id)
            addedFollowers?.let {
                gameRepository.startNewGame(it.toList())
                _startGame.value = true
            }
        }
    }

    fun tryToAddOnList(user: GameUserDTO){
        if (isFollowerOnList(user) == false){
            addFollowerToList(user)
        } else {
            removeFollowerFromList(user)
        }
    }

    fun tryToAddOnList(user: GameUserDTO){
        if (isFollowerOnList(user) == false){
            addFollowerToList(user)
        } else {
            removeFollowerFromList(user)
        }
    }

}