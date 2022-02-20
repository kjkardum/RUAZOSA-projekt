package hr.fer.ruazosa.kviz2022.network.DTOs.authentication

data class UserRegisterDTO(
    val email: String,
    val username: String,
    val password: String,
    val confirmPassword: String,
)
