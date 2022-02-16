package hr.fer.ruazosa.kviz2022.forms

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameStartForm(
    val userIds: List<Int>
)