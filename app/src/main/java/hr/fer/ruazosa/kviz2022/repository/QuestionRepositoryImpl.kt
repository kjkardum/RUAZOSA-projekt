package hr.fer.ruazosa.kviz2022.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.fer.ruazosa.kviz2022.domain.Question
import hr.fer.ruazosa.kviz2022.network.RemoteDemoApiService
import hr.fer.ruazosa.kviz2022.repository.interfaces.QuestionsRepository
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val remoteDemoApiService: RemoteDemoApiService
) : QuestionsRepository {
    override fun getQuestions(): LiveData<List<Question>> {
        return MutableLiveData(listOf(Question(
            title = "Question title",
            correctAnswer = "This is correct answer",
            cost = 800L
        )))
    }
}