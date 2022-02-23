package hr.fer.ruazosa.kviz2022.game.question

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.game.NewGameDTO
import hr.fer.ruazosa.kviz2022.network.dto.game.QuestionDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import hr.fer.ruazosa.kviz2022.repository.interfaces.UserRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GameQuestionViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    val answer = MutableLiveData<String>()

    private val question = MutableLiveData<QuestionDTO>()

    private val _answerValid = MutableLiveData<Boolean>()
    val answerValid: LiveData<Boolean> get() = _answerValid

    private val _answerInvalid = MutableLiveData<Boolean>()
    val answerInvalid: LiveData<Boolean> get() = _answerInvalid

    init {
        getQuestion()
    }

    fun answerQuestion(){
        viewModelScope.launch {
            val answerStatus = gameRepository.answerLastQuestion(1, answer.value?:"")
            if (answerStatus == 0){
                _answerInvalid.value = true
            } else {
                _answerValid.value = true
            }
            getQuestion()
        }
    }

    private fun getQuestion(){
        viewModelScope.launch {
            question.value = gameRepository.getNextQuestion(1)
            Timber.d("question fetched!")
        }
    }
}