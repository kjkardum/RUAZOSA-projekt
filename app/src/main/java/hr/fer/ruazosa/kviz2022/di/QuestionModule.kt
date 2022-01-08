package hr.fer.ruazosa.kviz2022.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import hr.fer.ruazosa.kviz2022.repository.QuestionRepositoryImpl
import hr.fer.ruazosa.kviz2022.repository.QuestionsRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class QuestionModule {
    @Binds
    abstract fun getQuestionSource(repository: QuestionRepositoryImpl): QuestionsRepository
}