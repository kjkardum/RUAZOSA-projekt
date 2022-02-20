package hr.fer.ruazosa.kviz2022.repository.interfaces

import hr.fer.ruazosa.kviz2022.network.DTOs.UserDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.AuthenticationResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.ResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.UserRegisterDTO

interface UserRepository {
    suspend fun authenticateAsync(email: String, password: String): ResponseDTO<AuthenticationResponseDTO>
    suspend fun registerAsync(model: UserRegisterDTO): ResponseDTO<String>
    fun isAuthenticated(): Boolean
    fun getUser(): UserDTO?
    fun logoutUser(): Boolean
}