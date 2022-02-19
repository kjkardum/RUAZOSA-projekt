package hr.fer.ruazosa.kviz2022.network.DTOs

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameStartDTO(
    val userIds: List<Int>
)