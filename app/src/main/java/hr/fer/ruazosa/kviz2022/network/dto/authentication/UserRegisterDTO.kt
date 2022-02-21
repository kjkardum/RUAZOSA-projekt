package hr.fer.ruazosa.kviz2022.network.dto.authentication

data class UserRegisterDTO(
    val email: String,
    val username: String,
    val password: String,
    val confirmPassword: String,
)
