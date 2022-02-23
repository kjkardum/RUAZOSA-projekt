package hr.fer.ruazosa.kviz2022.game.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.network.dto.game.GameLeaderboardResponseDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameResultViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _gameEnd = MutableLiveData<Boolean>()
    val gameEnd: LiveData<Boolean> get() = _gameEnd

    private val _results = MutableLiveData<GameLeaderboardResponseDTO>()
    val results: LiveData<GameLeaderboardResponseDTO> get() = _results

    init {
        getScoreBoard()
        endStatus()
    }

    private fun getScoreBoard(){
        viewModelScope.launch {
            _results.value = gameRepository.getGameLeaderboard(gameRepository.getLastStartedGameId())
        }
    }

    private fun endStatus(){

    }

    fun endGame(){
        _gameEnd.value = true
    }

}