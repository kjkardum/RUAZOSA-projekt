package hr.fer.ruazosa.kviz2022.network.DTOs;

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO(
    val userId: String,
    val code: String,
)