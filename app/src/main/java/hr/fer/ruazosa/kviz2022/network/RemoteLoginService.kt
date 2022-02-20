package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.DTOs.UserDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.AuthenticationResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.ResponseDTO
import hr.fer.ruazosa.kviz2022.network.DTOs.authentication.UserLoginDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

interface RemoteLoginService {
    @POST("Account/register")
    fun registerNewAccount(@Body register: POST): ResponseDTO<String>

    @POST("Account/authenticate")
    suspend fun authenticateAccount(@Body login: UserLoginDTO): ResponseDTO<AuthenticationResponseDTO>

    @POST("Account/forgot-password")
    fun passwordforgotten(@Body passwordforgotten: POST)

    @POST("Account/reset-password")
    fun resetPasswordToken(@Body passwordreset: POST)

    @GET("Account/confirm-email")
    fun confirmMail(): Deferred<List<UserDTO>>

}