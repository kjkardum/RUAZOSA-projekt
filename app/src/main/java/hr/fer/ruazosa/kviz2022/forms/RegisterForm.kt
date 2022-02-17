package hr.fer.ruazosa.kviz2022.forms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterForm (
    @Json(name = "email")
    val email: String,
    @Json(name = "userName")
    val userName: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "confirmPassword")
    val confirmPassword: String
    )