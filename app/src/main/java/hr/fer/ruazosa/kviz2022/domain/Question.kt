package hr.fer.ruazosa.kviz2022.domain

data class Question(
    val title: String,
    val correctAnswer: String,
    val cost: Long,
)
