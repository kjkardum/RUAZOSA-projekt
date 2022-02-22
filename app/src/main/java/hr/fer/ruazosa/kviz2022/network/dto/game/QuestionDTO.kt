package hr.fer.ruazosa.kviz2022.network.dto.game

data class QuestionDTO(
    val id: Int,
    val category: String,
    val difficulty: Int,
    val questionText: String,
    val answer: String?,
    val jServiceId: Int,
    val gameId: Int
)