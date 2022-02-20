package hr.fer.ruazosa.kviz2022.repository.interfaces

import androidx.lifecycle.LiveData
import hr.fer.ruazosa.kviz2022.domain.Question

interface QuestionsRepository {
    fun getQuestions(): LiveData<List<Question>>
}