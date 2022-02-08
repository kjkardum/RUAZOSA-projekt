package hr.fer.ruazosa.kviz2022.network.DTOs

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class GameDTO(
    val id: Int,
    val startTime: Date,
    val questions: List<QuestionDTO>
)