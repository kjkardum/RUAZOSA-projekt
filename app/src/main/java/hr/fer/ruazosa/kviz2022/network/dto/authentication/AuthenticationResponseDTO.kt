package hr.fer.ruazosa.kviz2022.network.dto.authentication

data class AuthenticationResponseDTO(
    val id: Int,
    val username: String?,
    val email: String?,
    val roles: List<String>?,
    val isVerified: Boolean,
    val jwToken: String,
)
