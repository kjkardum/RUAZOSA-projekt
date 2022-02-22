package hr.fer.ruazosa.kviz2022.network

import hr.fer.ruazosa.kviz2022.network.dto.GameUserDTO
import hr.fer.ruazosa.kviz2022.network.dto.UserDTO
import hr.fer.ruazosa.kviz2022.network.dto.authentication.AuthenticationResponseDTO
import hr.fer.ruazosa.kviz2022.network.dto.authentication.ResponseDTO
import hr.fer.ruazosa.kviz2022.network.dto.authentication.UserLoginDTO
import hr.fer.ruazosa.kviz2022.network.dto.authentication.UserRegisterDTO
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

interface RemoteLoginService {
    @GET("Account/getUser")
    suspend fun getUser(): GameUserDTO

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