package hr.fer.ruazosa.kviz2022.forms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginForm(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)