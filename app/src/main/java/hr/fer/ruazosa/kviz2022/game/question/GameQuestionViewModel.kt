package hr.fer.ruazosa.kviz2022.game.question

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.game.QuestionDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameQuestionViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    val answer = MutableLiveData<String>()

    private val _question = MutableLiveData<String>("")
    val question: LiveData<String> get() = _question

    fun answerQuestion(){
        viewModelScope.launch {
            val answerStatus = gameRepository.answerLastQuestion(1, answer.value?:"")
            if (answerStatus == 0){

            } else {

            }
        }
    }
}