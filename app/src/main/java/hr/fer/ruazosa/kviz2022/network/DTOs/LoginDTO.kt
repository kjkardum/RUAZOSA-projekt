package hr.fer.ruazosa.kviz2022.network.DTOs

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginDTO(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)