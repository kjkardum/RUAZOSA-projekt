package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.UserDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.AuthenticationResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.ResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.UserLoginDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.UserRegisterDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

interface RemoteLoginService {
    @POST("Account/register")
    suspend fun registerNewAccount(@Body register: UserRegisterDTO): ResponseDTO<String>

    @POST("Account/authenticate")
    suspend fun authenticateAccount(@Body login: UserLoginDTO): ResponseDTO<AuthenticationResponseDTO>

    @POST("Account/forgot-password")
    suspend fun passwordforgotten(@Body passwordforgotten: POST)

    @POST("Account/reset-password")
    suspend fun resetPasswordToken(@Body passwordreset: POST)

    @GET("Account/confirm-email")
    suspend fun confirmMail(): Deferred<List<UserDTO>>

}