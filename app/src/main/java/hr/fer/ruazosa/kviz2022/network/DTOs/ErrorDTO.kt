package hr.fer.ruazosa.kviz2022.network.DTOs

import com.squareup.moshi.Json

data class ErrorDTO(
    @Json(name = "ConfirmPassword")
    val ConfirmPassword: List<String>,
    @Json(name = "Email")
    val Email: List<String>,
    @Json(name = "Password")
    val Password: List<String>,
    @Json(name = "UserName")
    val UserName: List<String>
)