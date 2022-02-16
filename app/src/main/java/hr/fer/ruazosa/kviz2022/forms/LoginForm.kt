package hr.fer.ruazosa.kviz2022.forms

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginForm(
    val email: String,
    val password: String
)