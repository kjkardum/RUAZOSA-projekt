package hr.fer.ruazosa.kviz2022.forms

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterForm (
    val email: String,
    val userName: String,
    val password: String,
    val confirmPassword: String
    )