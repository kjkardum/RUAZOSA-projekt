package hr.fer.ruazosa.kviz2022.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.fer.ruazosa.kviz2022.repository.QuestionsRepository
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val repository: QuestionsRepository
) : ViewModel() {

    private val _demoCounter = MutableLiveData<Int>(0)
    val demoCounter: LiveData<Int> get() = _demoCounter

    private val _achievements = MutableLiveData<List<String>>()
    val achievements: LiveData<List<String>> get() = _achievements

    private val _navigateToSomewhere = MutableLiveData<Boolean>()
    val navigateToSomewhere: LiveData<Boolean> get() = _navigateToSomewhere

    fun navigate() {
        _navigateToSomewhere.value = true
        _demoCounter.value = _demoCounter.value?.plus(1)
        Timber.i("Repository getQuestion: ${repository.getQuestions().value?.get(0)?.correctAnswer}")
    }
    fun doneNavigating() {
        _navigateToSomewhere.value = false
    }

    override fun onCleared() {
        Timber.i("HomepageViewModel cleared")
        super.onCleared()
    }

}