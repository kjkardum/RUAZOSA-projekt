package hr.fer.ruazosa.kviz2022.network.dto;

data class UserDTO(
    val userId: Int,
    val email: String,
    val jwToken: String,
)