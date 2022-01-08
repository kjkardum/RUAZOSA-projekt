package hr.fer.ruazosa.kviz2022.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.fer.ruazosa.kviz2022.domain.Question
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(

    ) : QuestionsRepository {
    override fun getQuestions(): LiveData<List<Question>> {
        return MutableLiveData(listOf(Question(
            title = "Question title",
            correctAnswer = "This is correct answer",
            cost = 800L
        )))
    }
}