package hr.fer.ruazosa.kviz2022.game.question

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.network.dto.game.QuestionDTO
import hr.fer.ruazosa.kviz2022.repository.interfaces.GameRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GameQuestionViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {
    private val _timer = object: CountDownTimer(20*1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            _timerSeconds.value = (millisUntilFinished / 1000).toInt()
        }

        override fun onFinish() {
            answerQuestion()
        }
    }

    val answer = MutableLiveData<String>()

    private val _timerSeconds = MutableLiveData<Int>(0)
    val timerSeconds: LiveData<Int> get() = _timerSeconds

    private val _question = MutableLiveData<QuestionDTO>()
    val question: LiveData<QuestionDTO> get() = _question

    private val _answerValid = MutableLiveData<Boolean>()
    val answerValid: LiveData<Boolean> get() = _answerValid

    private val _answerInvalid = MutableLiveData<Boolean>()
    val answerInvalid: LiveData<Boolean> get() = _answerInvalid

    private val _finishGame = MutableLiveData<Boolean>()
    val finishGame: LiveData<Boolean> get() = _finishGame

    init {
        getQuestion()
    }

    fun answerQuestion(){
        _timer.cancel();
        viewModelScope.launch {
            val answerStatus = gameRepository.answerLastQuestion(gameRepository.getLastStartedGameId(), answer.value?:"")
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
            _question.value = gameRepository.getNextQuestion(gameRepository.getLastStartedGameId())
            if (_question.value == null) {
                _finishGame.value = true
            } else {
                _timer.start()
                Timber.d("question fetched!")
            }
        }
    }
}