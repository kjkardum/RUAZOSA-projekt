package hr.fer.ruazosa.kviz2022.network.dto.game
import java.util.*

data class GameDTO(
    val id: Int,
    val startTime: Date,
    val questions: List<QuestionDTO>?
)