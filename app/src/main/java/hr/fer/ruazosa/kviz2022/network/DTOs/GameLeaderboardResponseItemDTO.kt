package hr.fer.ruazosa.kviz2022.network.DTOs

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameLeaderboardResponseItemDTO(
    val userId: Int,
    val userName: String,
    val numberOfPoints: Int,
)
