package hr.fer.ruazosa.kviz2022.forms

import com.squareup.moshi.Json

data class Errors(
    @Json(name = "ConfirmPassword")
    val ConfirmPassword: List<String>,
    @Json(name = "Email")
    val Email: List<String>,
    @Json(name = "Password")
    val Password: List<String>,
    @Json(name = "UserName")
    val UserName: List<String>
)